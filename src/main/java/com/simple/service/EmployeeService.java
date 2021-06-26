package com.simple.service;

import com.simple.dto.FilterDto;
import com.simple.dto.create.request.EmployeeCreateRequest;
import com.simple.dto.create.response.EmployeeCreateResponse;
import com.simple.dto.get.response.EmployeeGetResponse;
import com.simple.dto.update.request.EmployeeUpdateRequest;
import com.simple.dto.update.response.EmployeeUpdateResponse;
import com.simple.entity.Employee;
import com.simple.mapper.EmployeeMapper;
import com.simple.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public EmployeeCreateResponse add(EmployeeCreateRequest employeeCreateRequest) {
        Employee employee = employeeMapper.mapCreateEmployeeRequestToEmployee(employeeCreateRequest);
        employee = employeeRepository.save(employee);
        return employeeMapper.mapEmployeeToCreateEmployeeResponse(employee);
    }

    @Transactional
    public void delete(long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(NullPointerException::new);
        employeeRepository.delete(employee);
    }

    public EmployeeGetResponse findById(long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(NullPointerException::new);
        return employeeMapper.mapEmployeeToGetEmployeeResponse(employee);
    }

    public List<EmployeeGetResponse> findAll(Pageable pageable) {
        List<Employee> employees = entityManager
                .createQuery("select distinct e from Employee e order by e.id")
                .setFirstResult(pageable.getPageNumber())
                .setMaxResults(pageable.getPageSize())
                .getResultList();
        return employeeMapper.mapEmployeeListToGetEmployeeResponseList(employees);
    }

    public EmployeeUpdateResponse update(long id, EmployeeUpdateRequest employeeUpdateRequest) {
        Employee employee = employeeRepository.findById(id).orElseThrow(NullPointerException::new);
        updateEmployeeFromRequestDto(employeeUpdateRequest, employee);
        employee = employeeRepository.save(employee);
        return employeeMapper.mapEmployeeToUpdateEmployeeResponse(employee);
    }

    public List<EmployeeGetResponse> find(FilterDto filterDto, Pageable pageable) {
        if (filterDto.getIdNumber() != null) {
            List<Employee> employees = findByIdNumber(filterDto.getIdNumber(), pageable);
            return employeeMapper.mapEmployeeListToGetEmployeeResponseList(employees);
        }

        if (filterDto.getBirthDateMore() != null) {
            List<Employee> employees = findByBirthDateMore(filterDto.getBirthDateMore(), pageable);
            return employeeMapper.mapEmployeeListToGetEmployeeResponseList(employees);
        }

        if (filterDto.getBirthDateLess() != null) {
            List<Employee> employees = findByBirthDateLess(filterDto.getBirthDateLess(), pageable);
            return employeeMapper.mapEmployeeListToGetEmployeeResponseList(employees);
        }

        return findAll(pageable);
    }

    private List<Employee> findByIdNumber(String idNumber, Pageable pageable) {
        return entityManager
                .createQuery("select distinct e from Employee e where e.idNumber like " +
                        ":idNumber order by e.id")
                .setParameter("idNumber", "%" + idNumber + "%")
                .setFirstResult(pageable.getPageNumber())
                .setMaxResults(pageable.getPageSize())
                .getResultList();
    }

    private List<Employee> findByBirthDateMore(Date birthDateMore, Pageable pageable) {
        return entityManager
                .createQuery("select distinct e from Employee e where e.birthDate > :birthDateMore")
                .setParameter("birthDateMore", birthDateMore)
                .setFirstResult(pageable.getPageNumber())
                .setMaxResults(pageable.getPageSize())
                .getResultList();
    }

    private List<Employee> findByBirthDateLess(Date birthDateLess, Pageable pageable) {
        return entityManager
                .createQuery("select distinct e from Employee e where e.birthDate < :birthDateLess")
                .setParameter("birthDateLess", birthDateLess)
                .setFirstResult(pageable.getPageNumber())
                .setMaxResults(pageable.getPageSize())
                .getResultList();
    }

    private void updateEmployeeFromRequestDto(EmployeeUpdateRequest employeeUpdateRequest, Employee employee) {
        if (employeeUpdateRequest.getFirstName() != null) {
            employee.setFirstName(employeeUpdateRequest.getFirstName());
        }
        if (employeeUpdateRequest.getLastName() != null) {
            employee.setLastName(employeeUpdateRequest.getLastName());
        }
        if (employeeUpdateRequest.getPatronymic() != null) {
            employee.setPatronymic(employeeUpdateRequest.getPatronymic());
        }
        if (employeeUpdateRequest.getIdNumber() != null) {
            employee.setIdNumber(employeeUpdateRequest.getIdNumber());
        }
        if (employeeUpdateRequest.getBirthDate() != null) {
            employee.setBirthDate(employeeUpdateRequest.getBirthDate());
        }
        if (employeeUpdateRequest.getStatus() != null) {
            employee.setStatus(employeeUpdateRequest.getStatus());
        }
    }
}

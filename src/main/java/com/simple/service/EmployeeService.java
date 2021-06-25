package com.simple.service;

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
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public EmployeeCreateResponse add(EmployeeCreateRequest employeeCreateRequest){
        Employee employee = employeeMapper.mapCreateEmployeeRequestToEmployee(employeeCreateRequest);
        employee = employeeRepository.save(employee);
        return employeeMapper.mapEmployeeToCreateEmployeeResponse(employee);
    }

    @Transactional
    public void delete(long id){
        Employee employee = employeeRepository.findById(id).orElseThrow(NullPointerException::new);
        employeeRepository.delete(employee);
    }

    public EmployeeGetResponse findById(long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(NullPointerException::new);
        return employeeMapper.mapEmployeeToGetEmployeeResponse(employee);
    }

    public List<EmployeeGetResponse> findAll(Pageable pageable) {
        List<Employee> employees = employeeRepository.findAll(pageable).toList();
        return employeeMapper.mapEmployeeListToGetEmployeeResponseList(employees);
    }

    public EmployeeUpdateResponse update(long id, EmployeeUpdateRequest employeeUpdateRequest){
        Employee employee = employeeRepository.findById(id).orElseThrow(NullPointerException::new);
        updateEmployeeFromRequestDto(employeeUpdateRequest, employee);
        employee = employeeRepository.save(employee);
        return employeeMapper.mapEmployeeToUpdateEmployeeResponse(employee);
    }

    public List<EmployeeGetResponse> find(String idNumber, Pageable pageable, Integer page, Integer size) {
        if(idNumber == null){
            return findAll(pageable);
        }

        CriteriaQuery<Employee> query = getEmployeeCriteriaQuery(idNumber);

        List<Employee> employees = entityManager.createQuery(query).getResultList();

        if(page == null && size == null){
            return employeeMapper.mapEmployeeListToGetEmployeeResponseList(employees);
        } else {
            return employeeMapper.mapEmployeeListToGetEmployeeResponseList(employees).subList(page, size);
        }
    }

    private CriteriaQuery<Employee> getEmployeeCriteriaQuery(String idNumber) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> query = cb.createQuery(Employee.class);
        Root<Employee> employee = query.from(Employee.class);

        Path<String> idNumPath = employee.get("idNumber");

        List<Predicate> predicates = new ArrayList<>();

        predicates.add(cb.like(idNumPath, "%" + idNumber + "%"));

        query.select(employee)
                .where(cb.or(predicates.toArray(new Predicate[predicates.size()])));
        return query;
    }

    private void updateEmployeeFromRequestDto(EmployeeUpdateRequest employeeUpdateRequest, Employee employee) {
        if(employeeUpdateRequest.getFirstName() != null){
            employee.setFirstName(employeeUpdateRequest.getFirstName());
        }
        if(employeeUpdateRequest.getLastName() != null){
            employee.setLastName(employeeUpdateRequest.getLastName());
        }
        if(employeeUpdateRequest.getPatronymic() != null){
            employee.setPatronymic(employeeUpdateRequest.getPatronymic());
        }
        if(employeeUpdateRequest.getIdNumber() != null){
            employee.setIdNumber(employeeUpdateRequest.getIdNumber());
        }
        if(employeeUpdateRequest.getBirthDate() != null){
            employee.setBirthDate(employeeUpdateRequest.getBirthDate());
        }
        if(employeeUpdateRequest.getStatus() != null){
            employee.setStatus(employeeUpdateRequest.getStatus());
        }
    }
}

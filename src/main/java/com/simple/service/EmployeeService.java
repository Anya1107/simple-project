package com.simple.service;

import com.simple.dto.create.request.EmployeeCreateRequest;
import com.simple.dto.create.response.EmployeeCreateResponse;
import com.simple.dto.get.response.EmployeeGetResponse;
import com.simple.dto.update.request.EmployeeUpdateRequest;
import com.simple.dto.update.response.EmployeeUpdateResponse;
import com.simple.entity.Employee;
import com.simple.entity.Status;
import com.simple.mapper.EmployeeMapper;
import com.simple.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

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

    public List<EmployeeGetResponse> findAll() {
        List<Employee> employees = employeeRepository.findAll();
        return employeeMapper.mapEmployeeListToGetEmployeeResponseList(employees);
    }

    public EmployeeUpdateResponse update(long id, EmployeeUpdateRequest employeeUpdateRequest){
        Employee employee = employeeRepository.findById(id).orElseThrow(NullPointerException::new);
        updateEmployeeFromRequestDto(employeeUpdateRequest, employee);
        employee = employeeRepository.save(employee);
        return employeeMapper.mapEmployeeToUpdateEmployeeResponse(employee);
    }

    public List<EmployeeGetResponse> findByFilter(String idNumber){
        List<Employee> byFilter = employeeRepository.findByFilter(idNumber);
        return employeeMapper.mapEmployeeListToGetEmployeeResponseList(byFilter);
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

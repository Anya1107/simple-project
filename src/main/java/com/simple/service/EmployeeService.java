package com.simple.service;

import com.simple.dto.EmployeeDto;
import com.simple.entity.Employee;
import com.simple.mapper.EmployeeMapper;
import com.simple.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeDto findById(long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(NullPointerException::new);
        return employeeMapper.convertToDto(employee);
    }

    public List<EmployeeDto> findAll() {
        List<Employee> employees = employeeRepository.findAll();
        return employeeMapper.convertListToDto(employees);
    }
}

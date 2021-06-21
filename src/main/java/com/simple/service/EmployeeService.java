package com.simple.service;

import com.simple.dto.EmployeeDto;
import com.simple.entity.Employee;
import com.simple.mapper.EmployeeMapper;
import com.simple.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @Transactional
    public EmployeeDto add(EmployeeDto employeeDto){
        employeeRepository.save(employeeMapper.convertFromDto(employeeDto));
        return employeeDto;
    }

    public void delete(long id){
        Employee employee = employeeRepository.findById(id).orElseThrow(NullPointerException::new);
        employeeRepository.delete(employee);
    }

    public EmployeeDto findById(long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(NullPointerException::new);
        return employeeMapper.convertToDto(employee);
    }

    public List<EmployeeDto> findAll() {
        List<Employee> employees = employeeRepository.findAll();
        return employeeMapper.convertListToDto(employees);
    }

    public EmployeeDto update(long id, EmployeeDto employeeDto){
        Employee employee = employeeRepository.findById(id).orElseThrow(NullPointerException::new);
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setPatronymic(employeeDto.getPatronymic());
        employee.setIdNumber(employeeDto.getIdNumber());
        employee.setBirthDate(employeeDto.getBirthDate());
        employee.setStatus(employeeDto.getStatus());
        return employeeMapper.convertToDto(employee);
    }
}

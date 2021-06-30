package com.simple.service;

import com.simple.dto.RegistrationDto;
import com.simple.entity.Employee;
import com.simple.mapper.EmployeeMapper;
import com.simple.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class RegService {

    private final PasswordEncoder passwordEncoder;
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @Transactional
    public boolean add(RegistrationDto registrationDto){
        registrationDto.getUserDto().setPassword(passwordEncoder.encode(registrationDto.getUserDto().getPassword()));
        Employee employee = employeeMapper.mapRegDtoToEmployee(registrationDto);
        employeeRepository.save(employee);
        return true;
    }
}

package com.simple.mapper;

import com.simple.dto.EmployeeDto;
import com.simple.entity.Employee;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@NoArgsConstructor
public class EmployeeMapper {

    public EmployeeDto convertToDto(Employee employee){
        return EmployeeDto.builder()
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .patronymic(employee.getPatronymic())
                .idNumber(employee.getIdNumber())
                .birthDate(employee.getBirthDate())
                .status(employee.getStatus())
                .build();
    }

    public Employee convertFromDto(EmployeeDto employeeDto){
        return Employee.builder()
                .firstName(employeeDto.getFirstName())
                .lastName(employeeDto.getLastName())
                .patronymic(employeeDto.getPatronymic())
                .idNumber(employeeDto.getIdNumber())
                .birthDate(employeeDto.getBirthDate())
                .status(employeeDto.getStatus())
                .build();

    }

    public List<EmployeeDto> convertListToDto(List<Employee> employees){
        List<EmployeeDto> employeeDtos = new ArrayList<>();
        for (Employee employee : employees) {
            EmployeeDto employeeDto = convertToDto(employee);
            employeeDtos.add(employeeDto);
        }
        return employeeDtos;
    }
}

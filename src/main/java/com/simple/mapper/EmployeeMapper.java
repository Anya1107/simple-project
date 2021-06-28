package com.simple.mapper;

import com.simple.dto.UserDto;
import com.simple.dto.create.request.EmployeeCreateRequest;
import com.simple.dto.create.response.EmployeeCreateResponse;
import com.simple.dto.get.response.EmployeeGetResponse;
import com.simple.dto.update.request.EmployeeUpdateRequest;
import com.simple.dto.update.response.EmployeeUpdateResponse;
import com.simple.entity.Employee;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@NoArgsConstructor
public class EmployeeMapper {

    public Employee mapCreateEmployeeRequestToEmployee(EmployeeCreateRequest employeeCreateRequest){
        return Employee.builder()
                .firstName(employeeCreateRequest.getFirstName())
                .lastName(employeeCreateRequest.getLastName())
                .patronymic(employeeCreateRequest.getPatronymic())
                .idNumber(employeeCreateRequest.getIdNumber())
                .birthDate(employeeCreateRequest.getBirthDate())
                .status(employeeCreateRequest.getStatus())
                .build();

    }

    public EmployeeCreateResponse mapEmployeeToCreateEmployeeResponse(Employee employee){
        return EmployeeCreateResponse.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .patronymic(employee.getPatronymic())
                .idNumber(employee.getIdNumber())
                .birthDate(employee.getBirthDate())
                .status(employee.getStatus())
                .build();
    }

    public Employee mapUpdateEmployeeRequestToEmployee(EmployeeUpdateRequest employeeUpdateRequest){
        return Employee.builder()
                .firstName(employeeUpdateRequest.getFirstName())
                .lastName(employeeUpdateRequest.getLastName())
                .patronymic(employeeUpdateRequest.getPatronymic())
                .idNumber(employeeUpdateRequest.getIdNumber())
                .birthDate(employeeUpdateRequest.getBirthDate())
                .status(employeeUpdateRequest.getStatus())
                .build();
    }

    public EmployeeUpdateResponse mapEmployeeToUpdateEmployeeResponse(Employee employee){
        return EmployeeUpdateResponse.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .patronymic(employee.getPatronymic())
                .idNumber(employee.getIdNumber())
                .birthDate(employee.getBirthDate())
                .status(employee.getStatus())
                .build();
    }

    public EmployeeGetResponse mapEmployeeToGetEmployeeResponse(Employee employee){
        return EmployeeGetResponse.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .patronymic(employee.getPatronymic())
                .idNumber(employee.getIdNumber())
                .birthDate(employee.getBirthDate())
                .status(employee.getStatus())
                .build();
    }

    public List<EmployeeGetResponse> mapEmployeeListToGetEmployeeResponseList(List<Employee> employees){
        List<EmployeeGetResponse> employeeGetResponses = new ArrayList<>();
        for (Employee employee : employees) {
            EmployeeGetResponse employeeGetResponse = mapEmployeeToGetEmployeeResponse(employee);
            employeeGetResponses.add(employeeGetResponse);
        }
        return employeeGetResponses;
    }

    public UserDto mapEmployeeToUserDto(Employee employee){
        return UserDto.builder()
                .username(employee.getUsername())
                .password(employee.getPassword())
                .build();
    }
}

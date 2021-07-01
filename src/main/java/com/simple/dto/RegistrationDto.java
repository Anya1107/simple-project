package com.simple.dto;

import com.simple.dto.create.request.EmployeeCreateRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegistrationDto {
    private UserDto userDto;
    private EmployeeCreateRequest employee;
}

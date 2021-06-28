package com.simple.dto;

import com.simple.dto.get.response.EmployeeGetResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegistrationDto {
    private UserDto userDto;
    private EmployeeGetResponse employee;
}

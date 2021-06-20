package com.simple.dto;

import com.simple.entity.Status;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDto {
    private String firstName;
    private String lastName;
    private String patronymic;
    private String idNumber;
    private Date birthDate;
    private Status status;
}

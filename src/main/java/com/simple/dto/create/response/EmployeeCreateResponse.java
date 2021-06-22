package com.simple.dto.create.response;

import com.simple.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeCreateResponse {
    private long id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String idNumber;
    private Date birthDate;
    private Status status;
}

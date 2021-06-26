package com.simple.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class FilterDto {
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date birthDateMore;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date birthDateLess;

    private String idNumber;
}

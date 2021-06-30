package com.simple.controller;

import com.simple.dto.RegistrationDto;
import com.simple.service.RegService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/reg")
public class RegistrationController {

    private final RegService regService;

    public RegistrationController(RegService regService) {
        this.regService = regService;
    }

    @PostMapping
    public boolean save(@RequestBody RegistrationDto registrationDto){
        return regService.add(registrationDto);
    }
}

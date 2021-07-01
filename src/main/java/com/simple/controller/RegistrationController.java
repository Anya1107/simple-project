package com.simple.controller;

import com.simple.dto.RegistrationDto;
import com.simple.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/reg")
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping
    public boolean save(@RequestBody RegistrationDto registrationDto) {
        return registrationService.add(registrationDto);
    }
}

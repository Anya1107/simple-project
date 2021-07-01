package com.simple.controller;

import com.simple.dto.TokenDto;
import com.simple.dto.UserDto;
import com.simple.service.AuthorizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
public class AuthorizationController {

    private AuthorizationService authorizationService;

    @PostMapping
    public TokenDto authorization(@RequestBody UserDto userDto) {
        return authorizationService.authorization(userDto);
    }

    @GetMapping("/refresh")
    public TokenDto refresh(Authentication authentication) {
        return authorizationService.refresh(authentication);
    }
}

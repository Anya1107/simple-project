package com.simple.controller;

import com.simple.dto.TokenDto;
import com.simple.dto.UserDto;
import com.simple.service.AuthService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
public class AuthorizationController {

    private final AuthService authService;

    public AuthorizationController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public TokenDto auth(@RequestBody UserDto userDto){
        return authService.access(userDto);
    }

    @GetMapping("/refresh")
    public TokenDto refresh(Authentication authentication){
        return authService.refresh(authentication);
    }
}

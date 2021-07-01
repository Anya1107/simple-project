package com.simple.service;

import com.simple.dto.TokenDto;
import com.simple.dto.UserDto;
import com.simple.entity.Employee;
import com.simple.mapper.RoleMapper;
import com.simple.repository.EmployeeRepository;
import com.simple.security.jwt.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthorizationService {

    private final JwtUtil jwtUtil;
    private final EmployeeRepository employeeRepository;
    private final RoleMapper roleMapper;
    private final PasswordEncoder passwordEncoder;

    public TokenDto authorization(UserDto userDto) {
        Employee employee = employeeRepository.findByUsername(userDto.getUsername());
        if (!passwordEncoder.matches(userDto.getPassword(), employee.getPassword())) {
            throw new UsernameNotFoundException("Wrong password!");
        }

        List<String> roles = roleMapper.mapRoleListToString(employee.getRoles());

        String access = jwtUtil.generateAccessToken(userDto.getUsername(), roles);
        String refresh = jwtUtil.generateRefreshToken(userDto.getUsername());
        TokenDto tokenDto = new TokenDto();
        tokenDto.setAccess(access);
        tokenDto.setRefresh(refresh);
        return tokenDto;
    }

    public TokenDto refresh(Authentication authentication) {
        String username = authentication.getName();
        Employee employee = employeeRepository.findByUsername(username);
        List<String> roles = roleMapper.mapRoleListToString(employee.getRoles());
        String accessToken = jwtUtil.generateAccessToken(username, roles);
        String refreshToken = jwtUtil.generateRefreshToken(username);
        TokenDto tokenDto = new TokenDto();
        tokenDto.setAccess(accessToken);
        tokenDto.setRefresh(refreshToken);
        return tokenDto;
    }
}

package com.simple.service;

import com.simple.dto.TokenDto;
import com.simple.dto.UserDto;
import com.simple.entity.Employee;
import com.simple.mapper.RoleMapper;
import com.simple.repository.EmployeeRepository;
import com.simple.security.jwt.JwtProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthService {

    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private RoleMapper roleMapper;

    public TokenDto access(UserDto userDto){
        Employee employee = employeeRepository.findByUsername(userDto.getUsername());

        if(!passwordEncoder.matches(userDto.getPassword(), employee.getPassword())){
            throw new UsernameNotFoundException("Wrong passwrod!");
        }

        List<String> roles = roleMapper.mapRoleListToRoleDtosList(employee.getRoles());

        String accessToken = jwtProvider.generateAccessToken(userDto.getUsername(), roles);
        String refreshToken = jwtProvider.generateRefreshToken(userDto.getUsername());
        TokenDto tokenDto = new TokenDto();
        tokenDto.setAccess(accessToken);
        tokenDto.setRefresh(refreshToken);
        return tokenDto;
    }

    public TokenDto refresh(Authentication authentication){
        String name = authentication.getName();
        Employee employee = employeeRepository.findByUsername(name);
        List<String> roles = roleMapper.mapRoleListToRoleDtosList(employee.getRoles());
        String accessToken = jwtProvider.generateAccessToken(name, roles);
        String refreshToken = jwtProvider.generateRefreshToken(name);
        TokenDto tokenDto = new TokenDto();
        tokenDto.setAccess(accessToken);
        tokenDto.setRefresh(refreshToken);
        return tokenDto;
    }
}

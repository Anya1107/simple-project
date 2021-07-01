package com.simple.security.jwt;

import com.simple.entity.Employee;
import com.simple.mapper.RoleMapper;
import com.simple.repository.EmployeeRepository;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtProvider implements AuthenticationProvider {

    private final RoleMapper roleMapper;
    private final EmployeeRepository employeeRepository;

    public JwtProvider(RoleMapper roleMapper, EmployeeRepository employeeRepository) {
        this.roleMapper = roleMapper;
        this.employeeRepository = employeeRepository;
    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        Employee employee = employeeRepository.findByUsername(username);
        List<String> roleNames = roleMapper.mapRoleListToString(employee.getRoles());
        List<SimpleGrantedAuthority> roles = roleNames.stream().map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        User userDetails = new User(employee.getUsername(), employee.getPassword(), true, true,
                true, true, roles);
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}

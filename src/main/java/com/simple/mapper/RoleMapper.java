package com.simple.mapper;

import com.simple.dto.RoleDto;
import com.simple.entity.Role;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@NoArgsConstructor
public class RoleMapper {

    public Role mapRoleDtoToRole(RoleDto roleDto){
        Role role = new Role();
        role.setName(roleDto.getName());
        return role;
    }

    public RoleDto mapRoleToRoleDto(Role role){
        RoleDto roleDto = new RoleDto();
        roleDto.setName(role.getName());
        return roleDto;
    }

    public List<String> mapRoleListToRoleDtosList(List<Role> roles){
        List<String> roleDtos = new ArrayList<>();
        for (Role role : roles) {
            roleDtos.add(role.getName());
        }
        return roleDtos;
    }
}

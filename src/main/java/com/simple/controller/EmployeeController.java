package com.simple.controller;

import com.simple.dto.EmployeeDto;
import com.simple.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public EmployeeDto add(@RequestBody EmployeeDto employeeDto){
        return employeeService.add(employeeDto);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable long id){
        employeeService.delete(id);
    }

    @GetMapping(path = "/{id}")
    public EmployeeDto findById(@PathVariable long id) {
        return employeeService.findById(id);
    }

    @GetMapping
    public List<EmployeeDto> findAll() {
        return employeeService.findAll();
    }

    @PutMapping(path = "/{id}")
    public EmployeeDto update(@PathVariable long id, @RequestBody EmployeeDto employeeDto){
        return employeeService.update(id, employeeDto);
    }
}

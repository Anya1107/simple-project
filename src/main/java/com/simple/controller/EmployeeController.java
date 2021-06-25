package com.simple.controller;

import com.simple.dto.create.request.EmployeeCreateRequest;
import com.simple.dto.create.response.EmployeeCreateResponse;
import com.simple.dto.get.response.EmployeeGetResponse;
import com.simple.dto.update.request.EmployeeUpdateRequest;
import com.simple.dto.update.response.EmployeeUpdateResponse;
import com.simple.entity.Employee;
import com.simple.entity.Status;
import com.simple.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public EmployeeCreateResponse add(@RequestBody EmployeeCreateRequest employeeDto){
        return employeeService.add(employeeDto);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable long id){
        employeeService.delete(id);
    }

    @GetMapping(path = "/{id}")
    public EmployeeGetResponse findById(@PathVariable long id) {
        return employeeService.findById(id);
    }

    @GetMapping
    public List<EmployeeGetResponse> findAll() {
        return employeeService.findAll();
    }

    @PutMapping(path = "/{id}")
    public EmployeeUpdateResponse update(@PathVariable long id, @RequestBody EmployeeUpdateRequest employeeUpdateRequest){
        return employeeService.update(id, employeeUpdateRequest);
    }

    @GetMapping("/byFilter")
    public List<EmployeeGetResponse> findByFilter(@RequestParam String idNumber){
        return employeeService.findByFilter(idNumber);
    }
}

package com.simple.controller;

import com.simple.dto.create.request.EmployeeCreateRequest;
import com.simple.dto.create.response.EmployeeCreateResponse;
import com.simple.dto.get.response.EmployeeGetResponse;
import com.simple.dto.update.request.EmployeeUpdateRequest;
import com.simple.dto.update.response.EmployeeUpdateResponse;
import com.simple.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @PutMapping(path = "/{id}")
    public EmployeeUpdateResponse update(@PathVariable long id, @RequestBody EmployeeUpdateRequest employeeUpdateRequest){
        return employeeService.update(id, employeeUpdateRequest);
    }

    @GetMapping
    public List<EmployeeGetResponse> findBAllOrByFilter(@RequestParam(required = false) String idNumber, Pageable pageable,
                                                        @RequestParam(value = "page", required = false) Integer page,
                                                        @RequestParam(value = "size", required = false) Integer size){
        return employeeService.find(idNumber, pageable, page, size);
    }
}

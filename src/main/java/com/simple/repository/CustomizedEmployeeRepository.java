package com.simple.repository;

import com.simple.entity.Employee;

import java.util.List;

public interface CustomizedEmployeeRepository {
    List<Employee> findByFilter(String idNumber);
}

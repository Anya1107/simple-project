package com.simple.repository;

import com.simple.entity.CardAccount;
import com.simple.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardAccountRepository extends JpaRepository<CardAccount, Long>{
    List<CardAccount> findAllByEmployee(Employee employee);
}

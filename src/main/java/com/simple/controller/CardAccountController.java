package com.simple.controller;

import com.simple.dto.CardAccountDto;
import com.simple.service.CardAccountService;
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
@RequestMapping("employees/{employeeId}/cardAccounts")
@RequiredArgsConstructor
public class CardAccountController {

    private final CardAccountService cardAccountService;

    @PostMapping
    public CardAccountDto add(@PathVariable long employeeId, @RequestBody CardAccountDto cardAccountDto){
        return cardAccountService.add(employeeId, cardAccountDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        cardAccountService.delete(id);
    }

    @GetMapping(path = "/{id}")
    public CardAccountDto findById(@PathVariable long id){
        return cardAccountService.findById(id);
    }

    @GetMapping
    public List<CardAccountDto> findAllByEmployeeId(@PathVariable long employeeId){
        return cardAccountService.findAllByEmployee(employeeId);
    }

    @PutMapping(path = "/{id}")
    public CardAccountDto update(@PathVariable long id, @RequestBody CardAccountDto cardAccountDto){
        return cardAccountService.update(id, cardAccountDto);
    }
}

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
@RequestMapping("/cardAccounts")
@RequiredArgsConstructor
public class CardAccountController {

    private final CardAccountService cardAccountService;

    @PostMapping(path = "/employees/{id}")
    public CardAccountDto add(@PathVariable long id, @RequestBody CardAccountDto cardAccountDto){
        return cardAccountService.add(id, cardAccountDto);
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
    public List<CardAccountDto> findAll(){
        return cardAccountService.findAll();
    }

    @GetMapping(path = "/employees/{id}")
    public List<CardAccountDto> findAllByEmployee(@PathVariable long id){
        return cardAccountService.findAllByEmployee(id);
    }

    @PutMapping(path = "/{id}")
    public CardAccountDto update(@PathVariable long id, @RequestBody CardAccountDto cardAccountDto){
        return cardAccountService.update(id, cardAccountDto);
    }
}

package com.simple.controller;

import com.simple.dto.create.request.CardAccountCreateRequest;
import com.simple.dto.create.response.CardAccountCreateResponse;
import com.simple.dto.get.response.CardAccountGetResponse;
import com.simple.dto.update.request.CardAccountUpdateRequest;
import com.simple.dto.update.response.CardAccountUpdateResponse;
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
    public CardAccountCreateResponse add(@PathVariable long employeeId, @RequestBody CardAccountCreateRequest cardAccountCreateRequest){
        return cardAccountService.add(employeeId, cardAccountCreateRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        cardAccountService.delete(id);
    }

    @GetMapping(path = "/{id}")
    public CardAccountGetResponse findById(@PathVariable long id){
        return cardAccountService.findById(id);
    }

    @GetMapping
    public List<CardAccountGetResponse> findAllByEmployeeId(@PathVariable long employeeId){
        return cardAccountService.findAllByEmployee(employeeId);
    }

    @PutMapping(path = "/{id}")
    public CardAccountUpdateResponse update(@PathVariable long id, @RequestBody CardAccountUpdateRequest cardAccountUpdateRequest){
        return cardAccountService.update(id, cardAccountUpdateRequest);
    }
}

package com.simple.controller;

import com.simple.dto.CardDto;
import com.simple.service.CardService;
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
@RequestMapping("employees/{employeeId}/cardAccounts/{cardAccId}/cards")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    @PostMapping
    public CardDto add(@PathVariable long cardAccId, @RequestBody CardDto cardDto){
        return cardService.add(cardAccId, cardDto);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable long id){
        cardService.delete(id);
    }

    @GetMapping(path = "/{id}")
    public CardDto findById(@PathVariable long id){
        return cardService.findById(id);
    }

    @GetMapping
    public List<CardDto> findAllByCardAccId(@PathVariable long cardAccId){
        return cardService.findAllByCardAccountId(cardAccId);
    }

    @PutMapping(path = "/{id}")
    public CardDto update(@PathVariable long id, @RequestBody CardDto cardDto){
        return cardService.update(id, cardDto);
    }
}

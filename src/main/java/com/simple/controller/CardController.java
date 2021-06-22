package com.simple.controller;

import com.simple.dto.create.request.CardCreateRequest;
import com.simple.dto.create.response.CardCreateResponse;
import com.simple.dto.get.response.CardGetResponse;
import com.simple.dto.update.request.CardUpdateRequest;
import com.simple.dto.update.response.CardUpdateResponse;
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
@RequestMapping("/cardAccounts/{cardAccId}/cards")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    @PostMapping
    public CardCreateResponse add(@PathVariable long cardAccId, @RequestBody CardCreateRequest cardCreateRequest){
        return cardService.add(cardAccId, cardCreateRequest);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable long id){
        cardService.delete(id);
    }

    @GetMapping(path = "/{id}")
    public CardGetResponse findById(@PathVariable long id){
        return cardService.findById(id);
    }

    @GetMapping
    public List<CardGetResponse> findAllByCardAccId(@PathVariable long cardAccId){
        return cardService.findAllByCardAccountId(cardAccId);
    }

    @PutMapping(path = "/{id}")
    public CardUpdateResponse update(@PathVariable long id, @RequestBody CardUpdateRequest cardUpdateRequest){
        return cardService.update(id, cardUpdateRequest);
    }
}

package com.simple.service;

import com.simple.dto.CardDto;
import com.simple.entity.Card;
import com.simple.entity.CardAccount;
import com.simple.mapper.CardMapper;
import com.simple.repository.CardAccountRepository;
import com.simple.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;
    private final CardAccountRepository cardAccountRepository;
    private final CardMapper cardMapper;

    @Transactional
    public CardDto add(CardDto cardDto){
        CardAccount cardAccount = new CardAccount();
        cardAccount.setId(cardDto.getCardAccountId());
        cardRepository.save(cardMapper.convertFromDto(cardDto, cardAccount));
        return cardDto;
    }

    public void delete(long id){
        Card card = cardRepository.findById(id).orElseThrow(NullPointerException::new);
        cardRepository.delete(card);
    }

    public CardDto findById(long id){
        Card card = cardRepository.findById(id).orElseThrow(NullPointerException::new);
        return cardMapper.convertToDto(card);
    }

    public List<CardDto> findAll(){
        List<Card> cards = cardRepository.findAll();
        return cardMapper.convertListToDto(cards);
    }

    public CardDto update(long id, CardDto cardDto){
        Card card = cardRepository.findById(id).orElseThrow(NullPointerException::new);
        card.setCardFirstName(cardDto.getCardFirstName());
        card.setCardLastName(cardDto.getCardLastName());
        card.setLogicStatus(cardDto.getLogicStatus());
        return cardMapper.convertToDto(card);
    }
}

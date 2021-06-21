package com.simple.service;

import com.simple.dto.CardDto;
import com.simple.entity.Card;
import com.simple.entity.CardAccount;
import com.simple.mapper.CardMapper;
import com.simple.mapper.LogicStatusMapper;
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
    private final LogicStatusMapper logicStatusMapper;

    @Transactional
    public CardDto add(long cardAccountId, CardDto cardDto){
        CardAccount cardAccount = cardAccountRepository.findById(cardAccountId).orElseThrow(NullPointerException::new);
        Card card = cardMapper.convertFromDto(cardDto);
        card.setCardAccount(cardAccount);
        cardRepository.save(card);
        return logicStatusMapper.convertLogicStatus(cardMapper.convertToDto(card));
    }

    public void delete(long id){
        Card card = cardRepository.findById(id).orElseThrow(NullPointerException::new);
        cardRepository.delete(card);
    }

    public CardDto findById(long id){
        Card card = cardRepository.findById(id).orElseThrow(NullPointerException::new);
        return logicStatusMapper.convertLogicStatus(cardMapper.convertToDto(card));
    }

    public List<CardDto> findAllByCardAccountId(long cardAccId){
        List<Card> cards = cardRepository.findAllByCardAccountId(cardAccId);
        return logicStatusMapper.convertLogicStatuses(cardMapper.convertListToDto(cards));
    }

    public CardDto update(long id, CardDto cardDto){
        Card card = cardRepository.findById(id).orElseThrow(NullPointerException::new);
        if(cardDto.getCardFirstName() != null){
            card.setCardFirstName(cardDto.getCardFirstName());
        }
        if(cardDto.getCardLastName() != null){
            card.setCardLastName(cardDto.getCardLastName());
        }
        if(cardDto.getLogicStatus() != null){
            card.setLogicStatus(cardDto.getLogicStatus());
        }
        card = cardRepository.save(card);
        return logicStatusMapper.convertLogicStatus(cardMapper.convertToDto(card));
    }
}

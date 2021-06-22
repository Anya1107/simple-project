package com.simple.service;

import com.simple.dto.create.request.CardCreateRequest;
import com.simple.dto.create.response.CardCreateResponse;
import com.simple.dto.get.response.CardGetResponse;
import com.simple.dto.update.request.CardUpdateRequest;
import com.simple.dto.update.response.CardUpdateResponse;
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
    public CardCreateResponse add(long cardAccountId, CardCreateRequest cardCreateRequest){
        CardAccount cardAccount = cardAccountRepository.findById(cardAccountId).orElseThrow(NullPointerException::new);
        Card card = cardMapper.mapCreateCardRequestToCard(cardCreateRequest);
        card.setCardAccount(cardAccount);
        card = cardRepository.save(card);
        CardCreateResponse cardCreateResponse = cardMapper.mapCardToCreateCardResponse(card);
        cardCreateResponse.setLogicStatus(logicStatusMapper.convertStatusField(card.getLogicStatus()));
        return cardCreateResponse;
    }

    @Transactional
    public void delete(long id){
        Card card = cardRepository.findById(id).orElseThrow(NullPointerException::new);
        cardRepository.delete(card);
    }

    public CardGetResponse findById(long id){
        Card card = cardRepository.findById(id).orElseThrow(NullPointerException::new);
        card.setLogicStatus(logicStatusMapper.convertStatusField(card.getLogicStatus()));
        return cardMapper.mapCardToGetCardResponse(card);
    }

    public List<CardGetResponse> findAllByCardAccountId(long cardAccId){
        List<Card> cards = cardRepository.findAllByCardAccountId(cardAccId);
        return logicStatusMapper.convertLogicStatuses(cardMapper.mapCardListToGetCardResponseList(cards));
    }

    public CardUpdateResponse update(long id, CardUpdateRequest cardUpdateRequest){
        Card card = cardRepository.findById(id).orElseThrow(NullPointerException::new);
        updateCardFromRequestDto(cardUpdateRequest, card);
        card = cardRepository.save(card);
        return cardMapper.mapCardToUpdateCardResponse(card);
    }

    private void updateCardFromRequestDto(CardUpdateRequest cardUpdateRequest, Card card) {
        if(cardUpdateRequest.getCardFirstName() != null){
            card.setCardFirstName(cardUpdateRequest.getCardFirstName());
        }
        if(cardUpdateRequest.getCardLastName() != null){
            card.setCardLastName(cardUpdateRequest.getCardLastName());
        }
        if(cardUpdateRequest.getLogicStatus() != null){
            card.setLogicStatus(logicStatusMapper.convertStatusField(cardUpdateRequest.getLogicStatus()));
        }
    }
}

package com.simple.mapper;

import com.simple.dto.create.request.CardCreateRequest;
import com.simple.dto.create.response.CardCreateResponse;
import com.simple.dto.get.response.CardGetResponse;
import com.simple.dto.update.request.CardUpdateRequest;
import com.simple.dto.update.response.CardUpdateResponse;
import com.simple.entity.Card;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@NoArgsConstructor
public class CardMapper {

    public Card mapCreateCardRequestToCard(CardCreateRequest cardCreateRequest){
        return Card.builder()
                .logicStatus(cardCreateRequest.getLogicStatus())
                .number(cardCreateRequest.getNumber())
                .cardFirstName(cardCreateRequest.getCardFirstName())
                .cardLastName(cardCreateRequest.getCardLastName())
                .build();
    }

    public CardCreateResponse mapCardToCreateCardResponse(Card card){
        return CardCreateResponse.builder()
                .id(card.getId())
                .logicStatus(card.getLogicStatus())
                .number(card.getNumber())
                .cardFirstName(card.getCardFirstName())
                .cardLastName(card.getCardLastName())
                .build();
    }

    public Card mapUpdateCardRequestToCard(CardUpdateRequest cardUpdateRequest){
        return Card.builder()
                .logicStatus(cardUpdateRequest.getLogicStatus())
                .number(cardUpdateRequest.getNumber())
                .cardFirstName(cardUpdateRequest.getCardFirstName())
                .cardLastName(cardUpdateRequest.getCardLastName())
                .build();
    }

    public CardUpdateResponse mapCardToUpdateCardResponse(Card card){
        return CardUpdateResponse.builder()
                .id(card.getId())
                .logicStatus(card.getLogicStatus())
                .number(card.getNumber())
                .cardFirstName(card.getCardFirstName())
                .cardLastName(card.getCardLastName())
                .build();
    }

    public CardGetResponse mapCardToGetCardResponse(Card card){
        return CardGetResponse.builder()
                .id(card.getId())
                .logicStatus(card.getLogicStatus())
                .number(card.getNumber())
                .cardFirstName(card.getCardFirstName())
                .cardLastName(card.getCardLastName())
                .build();
    }

    public List<CardGetResponse> mapCardListToGetCardResponseList(List<Card> cards){
        List<CardGetResponse> cardDtos = new ArrayList<>();
        for (Card card : cards) {
            CardGetResponse cardDto = mapCardToGetCardResponse(card);
            cardDtos.add(cardDto);
        }
        return cardDtos;
    }
}

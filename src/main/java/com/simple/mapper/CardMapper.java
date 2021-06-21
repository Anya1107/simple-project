package com.simple.mapper;

import com.simple.dto.CardDto;
import com.simple.entity.Card;
import com.simple.entity.CardAccount;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@NoArgsConstructor
public class CardMapper {

    public CardDto convertToDto(Card card){
        return CardDto.builder()
                .logicStatus(card.getLogicStatus())
                .cardFirstName(card.getCardFirstName())
                .cardLastName(card.getCardLastName())
                .cardAccountId(card.getCardAccount().getId())
                .build();
    }

    public Card convertFromDto(CardDto cardDto, CardAccount cardAccount){
        return Card.builder()
                .logicStatus(cardDto.getLogicStatus())
                .cardFirstName(cardDto.getCardFirstName())
                .cardLastName(cardDto.getCardLastName())
                .cardAccount(cardAccount)
                .build();
    }

    public List<CardDto> convertListToDto(List<Card> cards){
        List<CardDto> cardDtos = new ArrayList<>();
        for (Card card : cards) {
            CardDto cardDto = convertToDto(card);
            cardDtos.add(cardDto);
        }
        return cardDtos;
    }
}

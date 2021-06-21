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
                .number(card.getNumber())
                .cardFirstName(card.getCardFirstName())
                .cardLastName(card.getCardLastName())
                .build();
    }

    public Card convertFromDto(CardDto cardDto){
        return Card.builder()
                .logicStatus(cardDto.getLogicStatus())
                .number(cardDto.getNumber())
                .cardFirstName(cardDto.getCardFirstName())
                .cardLastName(cardDto.getCardLastName())
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

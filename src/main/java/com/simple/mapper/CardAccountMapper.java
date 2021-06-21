package com.simple.mapper;

import com.simple.dto.CardAccountDto;
import com.simple.entity.CardAccount;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@NoArgsConstructor
public class CardAccountMapper {

    public CardAccountDto convertToDto(CardAccount cardAccount){
        return CardAccountDto.builder()
                .bill_number(cardAccount.getBill_number())
                .currency(cardAccount.getCurrency())
                .status(cardAccount.getStatus())
                .build();
    }

    public CardAccount convertFromDto(CardAccountDto cardAccountDto){
        return CardAccount.builder()
                .bill_number(cardAccountDto.getBill_number())
                .currency(cardAccountDto.getCurrency())
                .status(cardAccountDto.getStatus())
                .build();
    }

    public List<CardAccountDto> convertListToDto(List<CardAccount> cardAccounts){
        List<CardAccountDto> cardAccountDtos = new ArrayList<>();
        for (CardAccount cardAccount : cardAccounts) {
            CardAccountDto cardAccountDto = convertToDto(cardAccount);
            cardAccountDtos.add(cardAccountDto);
        }
        return cardAccountDtos;
    }
}

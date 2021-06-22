package com.simple.mapper;

import com.simple.dto.create.request.CardAccountCreateRequest;
import com.simple.dto.create.response.CardAccountCreateResponse;
import com.simple.dto.get.response.CardAccountGetResponse;
import com.simple.dto.get.response.CardGetResponse;
import com.simple.dto.update.request.CardAccountUpdateRequest;
import com.simple.dto.update.response.CardAccountUpdateResponse;
import com.simple.entity.CardAccount;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@NoArgsConstructor
public class CardAccountMapper {

    public CardAccount mapCreateCardAccountToCardAccount(CardAccountCreateRequest cardAccountCreateRequest){
        return CardAccount.builder()
                .bill_number(cardAccountCreateRequest.getBill_number())
                .currency(cardAccountCreateRequest.getCurrency())
                .status(cardAccountCreateRequest.getStatus())
                .build();
    }

    public CardAccountCreateResponse mapCardAccountToCreateCardAccountResponse(CardAccount cardAccount){
        return CardAccountCreateResponse.builder()
                .id(cardAccount.getId())
                .bill_number(cardAccount.getBill_number())
                .currency(cardAccount.getCurrency())
                .status(cardAccount.getStatus())
                .build();
    }

    public CardAccount mapUpdateCardAccountRequestToCardAccount(CardAccountUpdateRequest cardAccountUpdateRequest){
        return CardAccount.builder()
                .bill_number(cardAccountUpdateRequest.getBill_number())
                .currency(cardAccountUpdateRequest.getCurrency())
                .status(cardAccountUpdateRequest.getStatus())
                .build();
    }

    public CardAccountUpdateResponse mapCardAccountToUpdateCardAccountResponse(CardAccount cardAccount){
        return CardAccountUpdateResponse.builder()
                .id(cardAccount.getId())
                .bill_number(cardAccount.getBill_number())
                .currency(cardAccount.getCurrency())
                .status(cardAccount.getStatus())
                .build();
    }

    public CardAccountGetResponse mapCardAccountToGetCardAccountResponse(CardAccount cardAccount){
        return CardAccountGetResponse.builder()
                .id(cardAccount.getId())
                .bill_number(cardAccount.getBill_number())
                .currency(cardAccount.getCurrency())
                .status(cardAccount.getStatus())
                .build();
    }

    public List<CardAccountGetResponse> mapCardListToGetCardResponseList(List<CardAccount> cardAccounts){
        List<CardAccountGetResponse> cardAccountDtos = new ArrayList<>();
        for (CardAccount cardAccount : cardAccounts) {
            CardAccountGetResponse cardAccountDto = mapCardAccountToGetCardAccountResponse(cardAccount);
            cardAccountDtos.add(cardAccountDto);
        }
        return cardAccountDtos;
    }
}

package com.simple.service;

import com.simple.dto.create.request.CardAccountCreateRequest;
import com.simple.dto.create.response.CardAccountCreateResponse;
import com.simple.dto.get.response.CardAccountGetResponse;
import com.simple.dto.update.request.CardAccountUpdateRequest;
import com.simple.dto.update.response.CardAccountUpdateResponse;
import com.simple.entity.CardAccount;
import com.simple.entity.Employee;
import com.simple.mapper.CardAccountMapper;
import com.simple.repository.CardAccountRepository;
import com.simple.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardAccountService {

    private final CardAccountRepository cardAccountRepository;
    private final EmployeeRepository employeeRepository;
    private final CardAccountMapper cardAccountMapper;

    @Transactional
    public CardAccountCreateResponse add(long employeeId, CardAccountCreateRequest cardAccountCreateRequest){
        CardAccount cardAccount = cardAccountMapper.mapCreateCardAccountToCardAccount(cardAccountCreateRequest);
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(NullPointerException::new);
        cardAccount.setEmployee(employee);
        cardAccountRepository.save(cardAccount);
        return cardAccountMapper.mapCardAccountToCreateCardAccountResponse(cardAccount);
    }

    @Transactional
    public void delete(long id){
        CardAccount cardAccount = cardAccountRepository.findById(id).orElseThrow(NullPointerException::new);
        cardAccountRepository.delete(cardAccount);
    }

    public CardAccountGetResponse findById(long id){
        CardAccount cardAccount = cardAccountRepository.findById(id).orElseThrow(NullPointerException::new);
        return cardAccountMapper.mapCardAccountToGetCardAccountResponse(cardAccount);
    }

    public List<CardAccountGetResponse> findAllByEmployee(long employeeId){
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(NullPointerException::new);
        List<CardAccount> cardAccounts = cardAccountRepository.findAllByEmployee(employee);
        return cardAccountMapper.mapCardListToGetCardResponseList(cardAccounts);
    }

    public CardAccountUpdateResponse update(long id, CardAccountUpdateRequest cardAccountUpdateRequest){
        CardAccount cardAccount = cardAccountRepository.findById(id).orElseThrow(NullPointerException::new);
        updateCardAccountFromRequestDto(cardAccount, cardAccountUpdateRequest);
        cardAccount = cardAccountRepository.save(cardAccount);
        return cardAccountMapper.mapCardAccountToUpdateCardAccountResponse(cardAccount);
    }

    private void updateCardAccountFromRequestDto(CardAccount cardAccount, CardAccountUpdateRequest cardAccountUpdateRequest) {
        if(cardAccountUpdateRequest.getBillNumber() != null){
            cardAccount.setBillNumber(cardAccountUpdateRequest.getBillNumber());
        }
        if(cardAccountUpdateRequest.getCurrency() != null){
            cardAccount.setCurrency(cardAccountUpdateRequest.getCurrency());
        }
        if(cardAccountUpdateRequest.getStatus() != null){
            cardAccount.setStatus(cardAccountUpdateRequest.getStatus());
        }
    }
}

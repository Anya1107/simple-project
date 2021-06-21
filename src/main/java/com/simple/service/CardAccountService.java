package com.simple.service;

import com.simple.dto.CardAccountDto;
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
    public CardAccountDto add(long employeeId, CardAccountDto cardAccountDto){
        CardAccount cardAccount = cardAccountMapper.convertFromDto(cardAccountDto);
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(NullPointerException::new);
        cardAccount.setEmployee(employee);
        cardAccountRepository.save(cardAccount);
        return cardAccountDto;
    }

    public void delete(long id){
        CardAccount cardAccount = cardAccountRepository.findById(id).orElseThrow(NullPointerException::new);
        cardAccountRepository.delete(cardAccount);
    }

    public CardAccountDto findById(long id){
        CardAccount cardAccount = cardAccountRepository.findById(id).orElseThrow(NullPointerException::new);
        return cardAccountMapper.convertToDto(cardAccount);
    }

    public List<CardAccountDto> findAll(){
        List<CardAccount> cardAccounts = cardAccountRepository.findAll();
        return cardAccountMapper.convertListToDto(cardAccounts);
    }

    public List<CardAccountDto> findAllByEmployee(long employeeId){
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(NullPointerException::new);
        List<CardAccount> cardAccounts = cardAccountRepository.findAllByEmployee(employee);
        return cardAccountMapper.convertListToDto(cardAccounts);
    }

    public CardAccountDto update(long id, CardAccountDto cardAccountDto){
        CardAccount cardAccount = cardAccountRepository.findById(id).orElseThrow(NullPointerException::new);
        cardAccount.setBill_number(cardAccountDto.getBill_number());
        cardAccount.setCurrency(cardAccountDto.getCurrency());
        cardAccount.setStatus(cardAccountDto.getStatus());
        return cardAccountMapper.convertToDto(cardAccount);
    }
}

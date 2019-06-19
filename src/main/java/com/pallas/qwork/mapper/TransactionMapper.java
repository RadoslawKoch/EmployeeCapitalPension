package com.pallas.qwork.mapper;

import com.pallas.qwork.definitions.mappers.TransactionMapperDefinition;
import com.pallas.qwork.dto.NewTransactionDto;
import com.pallas.qwork.dto.TransactionInfoDto;
import com.pallas.qwork.entities.Account;
import com.pallas.qwork.entities.Transaction;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper 
    implements TransactionMapperDefinition{
    
    @Override
    public Transaction map(NewTransactionDto dto, Account account){
        Transaction trns = new Transaction();
        trns.setAccount(account);
        trns.setAmount(dto.getAmount());
        trns.setTime(LocalDateTime.now());
        trns.setTitle(dto.getTitle());
        trns.setType(dto.getType());
        return trns;
    }
    
    @Override
    public TransactionInfoDto map(Transaction transaction){
        TransactionInfoDto dto = new TransactionInfoDto();
        dto.setAccountId(transaction.getAccount().getId());
        dto.setAmount(transaction.getAmount());
        dto.setId(transaction.getId());
        dto.setTime(transaction.getTime());
        dto.setTitle(transaction.getTitle());
        dto.setType(transaction.getType());
        return dto;
    }
}

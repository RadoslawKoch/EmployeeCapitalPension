package com.pallas.qwork.definitions.service;

import com.pallas.qwork.dto.NewTransactionDto;
import com.pallas.qwork.dto.TransactionInfoDto;
import com.pallas.qwork.definitions.enums.TransactionType;
import com.pallas.qwork.exceptions.AccountNotFoundException;
import com.pallas.qwork.exceptions.TransactionNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TransactionServiceDefinition {
    
    public void add(NewTransactionDto dto) 
            throws AccountNotFoundException;
     
    public Page<TransactionInfoDto> getByAccountId(String accountId, Pageable page) 
            throws AccountNotFoundException;
    
    public Page<TransactionInfoDto> get(Pageable page);
    
    public Page<TransactionInfoDto> getByAccountIdAndType(String accountId, TransactionType type,Pageable page) 
            throws AccountNotFoundException;
    
    public Page<TransactionInfoDto> getByAccountIdAndAmountBetween(String accountId,BigDecimal base,BigDecimal max,Pageable page) 
            throws AccountNotFoundException;
    
    public Page<TransactionInfoDto> getByAccountIdAndTimeBetween(String accountId,LocalDateTime start,LocalDateTime end, Pageable page) 
            throws AccountNotFoundException;
    
    public TransactionInfoDto getById(long id) 
            throws TransactionNotFoundException;
    
    public void delete(long id) 
            throws TransactionNotFoundException;
}

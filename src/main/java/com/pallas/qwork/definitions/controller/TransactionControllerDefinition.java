package com.pallas.qwork.definitions.controller;

import com.pallas.qwork.definitions.enums.TransactionType;
import com.pallas.qwork.dto.NewTransactionDto;
import com.pallas.qwork.dto.TransactionInfoDto;
import com.pallas.qwork.exceptions.AccountNotFoundException;
import com.pallas.qwork.exceptions.TransactionNotFoundException;
import java.math.BigDecimal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TransactionControllerDefinition 
    extends CommunControllerDefinition<TransactionInfoDto,Long,NewTransactionDto> {
    
    Page<TransactionInfoDto> get(String id,BigDecimal min,BigDecimal max,Pageable page)
            throws TransactionNotFoundException, AccountNotFoundException;
    
    Page<TransactionInfoDto> get(String id,String start,String end,Pageable page)
            throws TransactionNotFoundException, AccountNotFoundException;
    
    Page<TransactionInfoDto> get(String id,TransactionType type,Pageable page)
            throws TransactionNotFoundException, AccountNotFoundException;
    
    public Page<TransactionInfoDto> get(String id,Pageable page)
            throws TransactionNotFoundException, AccountNotFoundException;
    
}

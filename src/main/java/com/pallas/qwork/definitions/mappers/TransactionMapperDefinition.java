package com.pallas.qwork.definitions.mappers;

import com.pallas.qwork.dto.NewTransactionDto;
import com.pallas.qwork.dto.TransactionInfoDto;
import com.pallas.qwork.entities.Transaction;


public interface TransactionMapperDefinition 
    extends MapperDefinition<Transaction,TransactionInfoDto,NewTransactionDto> {
}

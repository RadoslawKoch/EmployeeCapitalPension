package com.pallas.qwork.repo;

import com.pallas.qwork.entities.Transaction;
import com.pallas.qwork.definitions.enums.TransactionType;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository 
    extends PagingAndSortingRepository<Transaction,Long>{
    Page<Transaction> findByAccountId(String accountId, Pageable page);
    Page<Transaction> findByAccountIdAndType(String accountId, TransactionType type,Pageable page);
    Page<Transaction> findByAccountIdAndAmountBetween(String accountId,BigDecimal base,BigDecimal max,Pageable page);
    Page<Transaction> findByAccountIdAndTimeBetween(String accountId,LocalDateTime start,LocalDateTime end, Pageable page);
}

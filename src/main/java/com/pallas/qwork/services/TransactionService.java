package com.pallas.qwork.services;

import com.pallas.qwork.definitions.service.AuditableServiceDefinition;
import com.pallas.qwork.definitions.service.TransactionServiceDefinition;
import com.pallas.qwork.dto.NewTransactionDto;
import com.pallas.qwork.dto.TransactionInfoDto;
import com.pallas.qwork.entities.Account;
import com.pallas.qwork.entities.Transaction;
import com.pallas.qwork.definitions.enums.ActionType;
import com.pallas.qwork.definitions.enums.TransactionType;
import com.pallas.qwork.definitions.mappers.TransactionMapperDefinition;
import com.pallas.qwork.exceptions.AccountNotFoundException;
import com.pallas.qwork.exceptions.TransactionNotFoundException;
import com.pallas.qwork.repo.AccountRepository;
import com.pallas.qwork.repo.TransactionRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TransactionService 
    extends AuditableServiceDefinition
    implements TransactionServiceDefinition{
    
    @Autowired
    private TransactionRepository transactionRepo;
    
    @Autowired
    private AccountRepository accountRepo;
    
    @Autowired
    private TransactionMapperDefinition mapper;
      
    @Override
    public void add(NewTransactionDto dto) 
            throws AccountNotFoundException{
        Account account = accountRepo.findById(dto.getAccountId()).orElse(null);
        if(account==null){
            throw new AccountNotFoundException();
        }
        if(!account.isActive()){
            throw new AccountNotFoundException();
        }
        Transaction trns = this.transactionRepo.save(this.mapper.map(dto, account));
        account.getTransactions().add(trns);
        if(dto.getType()==TransactionType.DEPOSIT){
            account.setBalance(account.getBalance().add(dto.getAmount()));
        }else{
            account.setBalance(account.getBalance().subtract(dto.getAmount()));
        }
        this.accountRepo.save(account);
        this.loginfo(ActionType.CREATE, trns);
    }
    
    @Override
    public Page<TransactionInfoDto> getByAccountId(String accountId, Pageable page) 
            throws AccountNotFoundException{
        verifyAccount(accountId);
        return this.transactionRepo.findByAccountId(accountId, page).map(x->this.mapper.map(x));
    }
    
    @Override
    public Page<TransactionInfoDto> get(Pageable page){
        return this.transactionRepo.findAll(page).map(x->this.mapper.map(x));
    }
    
    @Override
    public Page<TransactionInfoDto> getByAccountIdAndType(String accountId, TransactionType type,Pageable page) 
            throws AccountNotFoundException{
        verifyAccount(accountId);
        return this.transactionRepo.findByAccountIdAndType(accountId, type,page).map(x->this.mapper.map(x));
    }
    
    @Override
    public Page<TransactionInfoDto> getByAccountIdAndAmountBetween(String accountId,BigDecimal base,BigDecimal max,Pageable page) 
            throws AccountNotFoundException{
        verifyAccount(accountId);
        return this.transactionRepo.findByAccountIdAndAmountBetween(accountId, base,max,page).map(x->this.mapper.map(x));
    }
    
    @Override
    public Page<TransactionInfoDto> getByAccountIdAndTimeBetween(String accountId,LocalDateTime start,LocalDateTime end, Pageable page) 
            throws AccountNotFoundException{
        verifyAccount(accountId);
        return this.transactionRepo.findByAccountIdAndTimeBetween(accountId, start,end,page).map(x->this.mapper.map(x));
    }
    
    @Override
    public TransactionInfoDto getById(long id) throws TransactionNotFoundException{
        return this.mapper.map(this.getInstance(id));
    }
    
    @Override
    public void delete(long id) throws TransactionNotFoundException{   
        Transaction trns = this.getInstance(id);
        Account account = accountRepo.findById(trns.getAccount().getId()).orElse(null);
        if(trns.getType()==TransactionType.DEPOSIT){
            account.setBalance(account.getBalance().subtract(trns.getAmount()));
        }else{
            account.setBalance(account.getBalance().add(trns.getAmount()));
        }
        this.accountRepo.save(account);
        this.transactionRepo.delete(trns);
        this.loginfo(ActionType.DELETE, trns);
    }
    
    //HELPER METHOD
    private Transaction getInstance(long id) throws TransactionNotFoundException{
        Transaction tmp = this.transactionRepo.findById(id).orElse(null);
        if(tmp==null){
            throw new TransactionNotFoundException();
        }
        return tmp;
    }
    
    private void verifyAccount(String id) throws AccountNotFoundException{
        if(!this.accountRepo.existsById(id)){
            throw new AccountNotFoundException();
        }
    }
    
}

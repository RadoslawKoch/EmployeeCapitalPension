package com.pallas.qwork.services;

import com.pallas.qwork.definitions.service.AccountServiceDefinition;
import com.pallas.qwork.definitions.service.AuditableServiceDefinition;
import com.pallas.qwork.dto.AccountInfoDto;
import com.pallas.qwork.dto.NewAccountDto;
import com.pallas.qwork.entities.Account;
import com.pallas.qwork.definitions.enums.ActionType;
import com.pallas.qwork.definitions.mappers.AccountMapperDefinition;
import com.pallas.qwork.exceptions.AccountNotFoundException;
import com.pallas.qwork.exceptions.DuplicateAccountException;
import com.pallas.qwork.repo.AccountRepository;
import com.pallas.qwork.repo.DeclarationRepository;
import com.pallas.qwork.repo.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AccountService 
    extends AuditableServiceDefinition 
    implements AccountServiceDefinition{
    
    @Autowired
    private AccountRepository accountRepo;
    
    @Autowired
    private AccountMapperDefinition mapper;
    
    @Autowired
    private DeclarationRepository declarationRepo;
    
    @Autowired
    private TransactionRepository transactionRepo;
    
    
    @Override
    public void add(NewAccountDto dto) throws DuplicateAccountException{
        Account account = this.accountRepo.findById(dto.getId()).orElse(null);
        if(account!=null){
            throw new DuplicateAccountException();
        }
        account = new Account(dto.getId(),dto.getEmail(),dto.getManagerId(),dto.getBalance(),dto.isActive());
        this.accountRepo.save(account); 
        this.loginfo(ActionType.CREATE, account);
    }
    
    @Override
    public AccountInfoDto get(String id) throws AccountNotFoundException {
        return mapper.map(this.getInstance(id));
    }
    
    @Override
    public Page<AccountInfoDto> get(Pageable page){
        return this.accountRepo.findAll(page).map(x->this.mapper.map(x));
    }
    
    @Override
    public Page<AccountInfoDto> get(Pageable page, boolean active){
        return this.accountRepo.findByActive(active,page).map(x->this.mapper.map(x));
    }
 
    @Override
    public void delete(String id) throws AccountNotFoundException {
        Account tmp = this.getInstance(id);
        tmp.getDeclarations().forEach(x->this.declarationRepo.delete(x));
        tmp.getTransactions().forEach(x->this.transactionRepo.delete(x));
        this.accountRepo.delete(tmp);
        this.loginfo(ActionType.DELETE, tmp);
    }
    
    @Override
    public void update(NewAccountDto dto) throws AccountNotFoundException{
        Account tmp = this.getInstance(dto.getId());
        this.accountRepo.save(this.mapper.map(dto, tmp));
        this.loginfo(ActionType.UPDATE, tmp);
    }
  
    //HELPER INNER METHOD
    private Account getInstance(String id) throws AccountNotFoundException {
        Account account = this.accountRepo.findById(id).orElse(null);
        if(account==null){
            throw new AccountNotFoundException();
        }
        return account;
    } 
}

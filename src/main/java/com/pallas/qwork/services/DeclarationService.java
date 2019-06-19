package com.pallas.qwork.services;

import com.pallas.qwork.definitions.service.AuditableServiceDefinition;
import com.pallas.qwork.definitions.service.DeclarationServiceDefinition;
import com.pallas.qwork.dto.DeclarationInfoDto;
import com.pallas.qwork.dto.NewDeclarationDto;
import com.pallas.qwork.entities.Account;
import com.pallas.qwork.entities.Declaration;
import com.pallas.qwork.definitions.enums.ActionType;
import com.pallas.qwork.definitions.mappers.DeclarationMapperDefinition;
import com.pallas.qwork.exceptions.AccountNotFoundException;
import com.pallas.qwork.exceptions.DeclarationNotFoundException;
import com.pallas.qwork.repo.AccountRepository;
import com.pallas.qwork.repo.DeclarationRepository;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DeclarationService 
    extends AuditableServiceDefinition
    implements DeclarationServiceDefinition{
    
    @Autowired
    private DeclarationRepository declarationRepo;
    
    @Autowired
    private AccountRepository accountRepo;
    
    @Autowired
    private DeclarationMapperDefinition declarationMapper;
    
    //SECURITY VERIFICATION NEEDED
    @Override
    public void add(NewDeclarationDto dto){
        Account account = accountRepo.findById(dto.getUserId()).orElse(null);
        if(account==null){
            account = new Account(dto.getUserId(),dto.getDecision());
            this.accountRepo.save(account);
        }
        Declaration decl = this.declarationRepo.save(declarationMapper.map(dto, account));
        account.getDeclarations().add(decl); 
        //UPDATING ACCOUNT STATE IF NECESSARY
        if(decl.isDecision()!=account.isActive()){
            account.setActive(decl.isDecision());
            this.accountRepo.save(account);
        }
        this.loginfo(ActionType.CREATE,decl);
    }
    
    @Override
    public void delete(long id) throws DeclarationNotFoundException{//ONLY ADMIN
        Declaration tmp = this.getInstance(id);
        this.declarationRepo.delete(tmp);
        this.loginfo(ActionType.DELETE,tmp);
    }
    
    @Override
    public Page<DeclarationInfoDto> getByAccountId(String accountId,Pageable page) 
            throws AccountNotFoundException{
        verifyAccount(accountId);
        return this.declarationRepo.findByAccountId(accountId, page)
                .map(x->this.declarationMapper.map(x));
    }
    
    @Override
    public Page<DeclarationInfoDto> get(Pageable page){
        return this.declarationRepo.findAll(page)
                .map(x->this.declarationMapper.map(x));
    }
    
    @Override
    public Page<DeclarationInfoDto> getByAccountIdAndDecision(String account,boolean decision,Pageable page) 
            throws AccountNotFoundException{
        verifyAccount(account);
        return this.declarationRepo.findByAccountIdAndDecision(account, decision, page)
                .map(x->this.declarationMapper.map(x));
    }
    @Override
    public Page<DeclarationInfoDto> getByAccountIdAndTimeBetween(String account, LocalDateTime start,LocalDateTime end,Pageable page) 
            throws AccountNotFoundException{
        verifyAccount(account);
        return this.declarationRepo.findByAccountIdAndTimeBetween(account, start, end, page)
                .map(x->this.declarationMapper.map(x));
    }
    
    @Override
    public DeclarationInfoDto getById(long id) throws DeclarationNotFoundException{
        return this.declarationMapper.map(this.getInstance(id));
    }
    
    //HELPER METHOD
    private Declaration getInstance(long id) throws DeclarationNotFoundException{
        Declaration decl = this.declarationRepo.findById(id).orElse(null);
        if(decl==null){
            throw new DeclarationNotFoundException();
        }
        return decl;
    }
    
    private void verifyAccount(String id) throws AccountNotFoundException{
        if(!this.accountRepo.existsById(id)){
            throw new AccountNotFoundException();
        }
    }
}

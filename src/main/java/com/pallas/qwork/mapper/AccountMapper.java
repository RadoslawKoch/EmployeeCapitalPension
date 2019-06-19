package com.pallas.qwork.mapper;

import com.pallas.qwork.definitions.mappers.AccountMapperDefinition;
import com.pallas.qwork.definitions.mappers.DeclarationMapperDefinition;
import com.pallas.qwork.dto.AccountInfoDto;
import com.pallas.qwork.dto.NewAccountDto;
import com.pallas.qwork.entities.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper
    implements AccountMapperDefinition{
    
    @Autowired
    private DeclarationMapperDefinition mapper;
    
    @Override
    public Account map(NewAccountDto dto, Account old){
        old.setActive(dto.isActive());
        old.setBalance(dto.getBalance());
        old.setEmail(dto.getEmail());
        old.setManagerId(dto.getManagerId());
        return old;
    }
    
    @Override
    public AccountInfoDto map(Account account){
        AccountInfoDto dto = new AccountInfoDto();
        dto.setBalance(account.getBalance());
        dto.setId(account.getId());
        dto.setEmail(account.getEmail());
        dto.setManagerId(account.getManagerId());
        if(!account.getDeclarations().isEmpty()){
            dto.setLastDeclaration(mapper.map(account.getDeclarations().get(account.getDeclarations().size()-1)));
        }
        dto.setStatus(account.isActive());
        return dto;
    }
}

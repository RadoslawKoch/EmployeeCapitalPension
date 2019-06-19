package com.pallas.qwork.mapper;

import com.pallas.qwork.definitions.mappers.DeclarationMapperDefinition;
import com.pallas.qwork.dto.DeclarationInfoDto;
import com.pallas.qwork.dto.NewDeclarationDto;
import com.pallas.qwork.entities.Account;
import com.pallas.qwork.entities.Declaration;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component
public class DeclarationMapper 
    implements DeclarationMapperDefinition{
    
    @Override
    public Declaration map(NewDeclarationDto dto, Account account){
        Declaration decl = new Declaration();
        decl.setAccount(account);
        decl.setContent(dto.getContent());
        decl.setDecision(dto.getDecision());
        decl.setTime(LocalDateTime.now());
        return decl;
    }
    
    @Override
    public DeclarationInfoDto map(Declaration decl){
        DeclarationInfoDto dto = new DeclarationInfoDto();
        dto.setAccountId(decl.getAccount().getId());
        dto.setContent(decl.getContent());
        dto.setDecision(decl.isDecision());
        dto.setId(decl.getId());
        dto.setTime(decl.getTime());
        return dto;
    }
   
}

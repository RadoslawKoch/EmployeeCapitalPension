package com.pallas.qwork.definitions.service;

import com.pallas.qwork.dto.DeclarationInfoDto;
import com.pallas.qwork.dto.NewDeclarationDto;
import com.pallas.qwork.exceptions.AccountNotFoundException;
import com.pallas.qwork.exceptions.DeclarationNotFoundException;
import java.time.LocalDateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DeclarationServiceDefinition {
    
    public void add(NewDeclarationDto dto);
 
    public void delete(long id) 
            throws DeclarationNotFoundException;
    
    public Page<DeclarationInfoDto> getByAccountId(String accountId,Pageable page) 
            throws AccountNotFoundException;
    
    public Page<DeclarationInfoDto> get(Pageable page);
    
    public Page<DeclarationInfoDto> getByAccountIdAndDecision(String account,boolean decision,Pageable page) 
            throws AccountNotFoundException;
    
    public Page<DeclarationInfoDto> getByAccountIdAndTimeBetween(String account, LocalDateTime start,LocalDateTime end,Pageable page) 
            throws AccountNotFoundException;
    
    public DeclarationInfoDto getById(long id) 
            throws DeclarationNotFoundException;
}

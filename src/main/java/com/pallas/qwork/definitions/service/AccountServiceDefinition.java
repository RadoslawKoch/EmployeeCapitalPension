package com.pallas.qwork.definitions.service;

import com.pallas.qwork.dto.AccountInfoDto;
import com.pallas.qwork.dto.NewAccountDto;
import com.pallas.qwork.exceptions.AccountNotFoundException;
import com.pallas.qwork.exceptions.DuplicateAccountException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AccountServiceDefinition {
    
    void add(NewAccountDto dto) throws DuplicateAccountException;
    
    AccountInfoDto get(String id) throws AccountNotFoundException;
    
    Page<AccountInfoDto> get(Pageable page);
    
    Page<AccountInfoDto> get(Pageable page, boolean active);
       
    void delete(String id) throws AccountNotFoundException;
    
    void update(NewAccountDto dto) throws AccountNotFoundException;
}

package com.pallas.qwork.definitions.controller;

import com.pallas.qwork.dto.DeclarationInfoDto;
import com.pallas.qwork.dto.NewDeclarationDto;
import com.pallas.qwork.exceptions.AccountNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DeclarationControllerDefinition 
    extends CommunControllerDefinition<DeclarationInfoDto,Long,NewDeclarationDto>{
    
    Page<DeclarationInfoDto> get(String id,Pageable page) 
            throws AccountNotFoundException;
    
    Page<DeclarationInfoDto> get(String id,boolean signed,Pageable page) 
            throws AccountNotFoundException;
    
    Page<DeclarationInfoDto> get(String id,String start,String end,Pageable page) 
            throws AccountNotFoundException;
}

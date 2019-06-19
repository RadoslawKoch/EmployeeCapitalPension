package com.pallas.qwork.definitions.controller;

import com.pallas.qwork.dto.AccountInfoDto;
import com.pallas.qwork.dto.NewAccountDto;
import com.pallas.qwork.exceptions.DeclarationNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;

public interface AccountControllerDefinition 
    extends CommunControllerDefinition<AccountInfoDto,String,NewAccountDto>,
        EditableControllerDefinition<NewAccountDto,String>{
    
    Page<AccountInfoDto> get(Pageable page,@PathVariable boolean active) 
            throws DeclarationNotFoundException;
    
}

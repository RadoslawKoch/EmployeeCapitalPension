package com.pallas.qwork.controller;

import com.pallas.qwork.definitions.controller.AccountControllerDefinition;
import com.pallas.qwork.dto.AccountInfoDto;
import com.pallas.qwork.dto.NewAccountDto;
import com.pallas.qwork.exceptions.AccountNotFoundException;
import com.pallas.qwork.exceptions.DeclarationNotFoundException;
import com.pallas.qwork.exceptions.DuplicateAccountException;
import com.pallas.qwork.definitions.error.ErrorCatcher;
import com.pallas.qwork.definitions.service.AccountServiceDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController 
    extends ErrorCatcher
    implements AccountControllerDefinition{
    
    @Autowired
    private AccountServiceDefinition accountService;
    
    @RequestMapping(value="/accounts",method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @Override
    public void add(@RequestBody @Validated NewAccountDto dto) 
            throws DuplicateAccountException{
        this.accountService.add(dto);
    }
    
    @RequestMapping(value="/accounts/{id}",method=RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public void put(@RequestBody @Validated NewAccountDto dto, @PathVariable String id) 
            throws AccountNotFoundException {
        dto.setId(id);
        this.accountService.update(dto);
    }
    
    @RequestMapping(value="/accounts/{id}",method=RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public void delete(@PathVariable String id) 
            throws AccountNotFoundException{
        this.accountService.delete(id);
    }
    
    @RequestMapping(value="/accounts/{id}",method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public AccountInfoDto get(@PathVariable String id) 
            throws AccountNotFoundException {
        return this.accountService.get(id);
    }
    
    @RequestMapping(value="/accounts",method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public Page<AccountInfoDto> get(Pageable page) 
            throws DeclarationNotFoundException{
        return this.accountService.get(page);
    }
    
    @RequestMapping(value="/accounts/active/{active}",method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public Page<AccountInfoDto> get(Pageable page,@PathVariable boolean active) 
            throws DeclarationNotFoundException{
        return this.accountService.get(page, active);
    }  
}

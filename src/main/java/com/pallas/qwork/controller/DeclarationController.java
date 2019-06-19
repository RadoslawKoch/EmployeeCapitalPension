package com.pallas.qwork.controller;

import com.pallas.qwork.definitions.controller.DeclarationControllerDefinition;
import com.pallas.qwork.dto.DeclarationInfoDto;
import com.pallas.qwork.dto.NewDeclarationDto;
import com.pallas.qwork.exceptions.DeclarationNotFoundException;
import com.pallas.qwork.definitions.error.ErrorCatcher;
import com.pallas.qwork.definitions.service.DeclarationServiceDefinition;
import com.pallas.qwork.exceptions.AccountNotFoundException;
import java.time.LocalDateTime;
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
public class DeclarationController 
    extends ErrorCatcher
    implements DeclarationControllerDefinition{
    
    @Autowired
    private DeclarationServiceDefinition declarationService;
    
    @RequestMapping(value="/declarations",method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @Override
    public void add(@RequestBody @Validated NewDeclarationDto dto){
        this.declarationService.add(dto);
    }
    
    @RequestMapping(value="/declarations/{id}",method=RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public void delete(@PathVariable Long id) 
            throws DeclarationNotFoundException{
        this.declarationService.delete(id);
    }
    
        
    @RequestMapping(value="/declarations/{id}",method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public DeclarationInfoDto get(@PathVariable Long id) 
            throws DeclarationNotFoundException{
        return this.declarationService.getById(id);
    }
    
    @RequestMapping(value="/declarations",method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public Page<DeclarationInfoDto> get(Pageable page){
        return this.declarationService.get(page);
    }
    
    @RequestMapping(value="/accounts/{id}/declarations",method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public Page<DeclarationInfoDto> get(@PathVariable String id,Pageable page) 
            throws AccountNotFoundException{
        return this.declarationService.getByAccountId(id, page);
    }
    
    @RequestMapping(value="/accounts/{id}/declarations/signed/{signed}",method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public Page<DeclarationInfoDto> get(@PathVariable String id,@PathVariable boolean signed,Pageable page) 
            throws AccountNotFoundException{
        return this.declarationService.getByAccountIdAndDecision(id, signed, page);
    }
    
    @RequestMapping(value="/accounts/{id}/declarations/{start}/{end}",method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public Page<DeclarationInfoDto> get(@PathVariable String id,@PathVariable String start,@PathVariable String end,Pageable page) 
            throws AccountNotFoundException{
        return this.declarationService.getByAccountIdAndTimeBetween(id, LocalDateTime.parse(start), LocalDateTime.parse(end), page);
    }   


}

package com.pallas.qwork.controller;

import com.pallas.qwork.definitions.controller.TransactionControllerDefinition;
import com.pallas.qwork.dto.NewTransactionDto;
import com.pallas.qwork.dto.TransactionInfoDto;
import com.pallas.qwork.definitions.enums.TransactionType;
import com.pallas.qwork.exceptions.AccountNotFoundException;
import com.pallas.qwork.exceptions.TransactionNotFoundException;
import com.pallas.qwork.definitions.error.ErrorCatcher;
import com.pallas.qwork.definitions.service.TransactionServiceDefinition;
import java.math.BigDecimal;
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
public class TransactionController 
    extends ErrorCatcher
    implements TransactionControllerDefinition{
    
    @Autowired
    private TransactionServiceDefinition transactionService;  
    
    @RequestMapping(value="/transactions/{id}",method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public TransactionInfoDto get(@PathVariable Long id)
            throws TransactionNotFoundException {
        return this.transactionService.getById(id);
    }
    
    @RequestMapping(value="/accounts/{id}/transactions",method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public Page<TransactionInfoDto> get(@PathVariable String id,Pageable page)
            throws TransactionNotFoundException, AccountNotFoundException {
        return this.transactionService.getByAccountId(id, page);
    }
    
    @RequestMapping(value="/accounts/{id}/transactions/amount/{min}/{max}",method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public Page<TransactionInfoDto> get(@PathVariable String id,@PathVariable BigDecimal min,@PathVariable BigDecimal max,Pageable page)
            throws TransactionNotFoundException, AccountNotFoundException {
        return this.transactionService.getByAccountIdAndAmountBetween(id, min, max, page);
    }
    
    @RequestMapping(value="/accounts/{id}/transactions/time/{start}/{end}",method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public Page<TransactionInfoDto> get(@PathVariable String id,@PathVariable String start,@PathVariable String end,Pageable page)
            throws TransactionNotFoundException, AccountNotFoundException {
        return this.transactionService.getByAccountIdAndTimeBetween(id, LocalDateTime.parse(start), LocalDateTime.parse(end), page);
    }
    
    @RequestMapping(value="/accounts/{id}/transactions/type/{type}",method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public Page<TransactionInfoDto> get(@PathVariable String id,@PathVariable TransactionType type,Pageable page)
            throws TransactionNotFoundException, AccountNotFoundException {
       return this.transactionService.getByAccountIdAndType(id, type, page);  
    }
    
    //ADMIN or AUTO
    @RequestMapping(value="/transactions",method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @Override
    public void add(@RequestBody @Validated NewTransactionDto dto) 
            throws AccountNotFoundException{
        this.transactionService.add(dto);
    }

    //ADMIN or AUTO
    @RequestMapping(value="/transactions/{id}",method=RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public void delete(@PathVariable Long id) 
            throws TransactionNotFoundException {
        this.transactionService.delete(id);
    }

    //ADMIN
    @RequestMapping(value="/transactions",method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public Page<TransactionInfoDto> get(Pageable page)
            throws TransactionNotFoundException {
        return this.transactionService.get(page);
    }

}

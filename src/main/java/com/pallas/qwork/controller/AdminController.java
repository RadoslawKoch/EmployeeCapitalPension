package com.pallas.qwork.controller;

import com.pallas.qwork.definitions.controller.AdminControllerDefinition;
import com.pallas.qwork.definitions.enums.ActionType;
import com.pallas.qwork.definitions.enums.RecordType;
import com.pallas.qwork.definitions.service.AuditServiceDefinition;
import com.pallas.qwork.entities.AuditRecord;
import com.pallas.qwork.errors.ErrorLogger;
import com.pallas.qwork.exceptions.RecordNotFoundException;
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
import com.pallas.qwork.definitions.error.ErrorCatcher;

@RestController
public class AdminController
    extends ErrorCatcher
    implements AdminControllerDefinition{
    
    @Autowired
    private ErrorLogger errorLogger;
    
    @Autowired
    private AuditServiceDefinition service;
    
    @RequestMapping(value="/errlog",method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public String getLogContent(){
        return this.errorLogger.getLogContent();
    }
    
    @RequestMapping(value="/errlog/clean",method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public void cleanLog(){
        this.errorLogger.cleanUp();
    }
    
    @RequestMapping(value="/audit/records",method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public Page<AuditRecord> getAuditRecords(Pageable page){
        return this.service.getAll(page);
    }
    
    @RequestMapping(value="/audit/records",method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @Override
    public void saveAuditRecord(@RequestBody @Validated AuditRecord record){
        this.service.saveInfo(record);
    }

    @RequestMapping(value="/audit/records/{id}",method=RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public void deleteAuditRecord(@PathVariable long id){
        this.service.delete(id);
    }
    
    @RequestMapping(value="/audit/records/{id}",method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public AuditRecord getAuditRecord(@PathVariable long id) throws RecordNotFoundException{
        return this.service.getAuditRecord(id);
    }
    
    @RequestMapping(value="/audit/records/time/{start}/{end}",method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public Page<AuditRecord> getAuditRecords(@PathVariable String start, @PathVariable String end,Pageable page){
        return this.service.getByTimeBetween(LocalDateTime.parse(start),LocalDateTime.parse(end),page);
    }
    
    @RequestMapping(value="/audit/records/author/{name}",method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public Page<AuditRecord> getAuditRecords(@PathVariable String name,Pageable page){
        return this.service.getByAuthorFullnameLike(name, page);
    }
    
    @RequestMapping(value="/audit/records/id/{id}",method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public Page<AuditRecord> getAuditRecordsById(@PathVariable String id,Pageable page){
        return this.service.getByRecordId(id, page);
    }
    
    @RequestMapping(value="/audit/records/type/{type}",method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public Page<AuditRecord> getAuditRecordsByType(@PathVariable RecordType type,Pageable page){
        return this.service.getByRecordType(type, page);
    }
    
    @RequestMapping(value="/audit/records/action/{type}",method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public Page<AuditRecord> getAuditRecordsByActionType(@PathVariable ActionType type,Pageable page){
        return this.service.findByActionType(type, page);
    }
}

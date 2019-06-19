package com.pallas.qwork.services;

import com.pallas.qwork.definitions.Auditable;
import com.pallas.qwork.definitions.service.AuditServiceDefinition;
import com.pallas.qwork.entities.AuditRecord;
import com.pallas.qwork.definitions.enums.ActionType;
import com.pallas.qwork.definitions.enums.RecordType;
import com.pallas.qwork.exceptions.RecordNotFoundException;
import com.pallas.qwork.repo.AuditRepository;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AuditService 
    implements AuditServiceDefinition{
    
    @Autowired
    private AuditRepository repo;
    
    @Override
    public void saveInfo(ActionType action,Auditable audit){
        //getPrincipal()--> for having info about actor
        this.repo.save(new AuditRecord(action, "default", "default", audit)); 
    }
    
    @Override
    public void saveInfo(AuditRecord record){
        this.repo.save(record);
    }
    
    @Override
    public void delete(long id){    
        this.repo.deleteById(id);
    }
    
    @Override
    public AuditRecord getAuditRecord(long id) throws RecordNotFoundException{
        AuditRecord record = this.repo.findById(id).orElse(null);
        if(record==null){
            throw new RecordNotFoundException();
        }
        return record;
    }
    
    @Override
    public Page<AuditRecord> getAll(Pageable page){
        return this.repo.findAll(page);
    }
    
    @Override
    public Page<AuditRecord> getByTimeBetween(LocalDateTime start, LocalDateTime end, Pageable page){
        return this.repo.findByTimeBetween(start, end, page);
    }
    
    @Override
    public Page<AuditRecord> getByAuthorFullnameLike(String fullName, Pageable page){
        return this.repo.findByAuthorFullnameLike(fullName, page);
    }
    
    @Override
    public Page<AuditRecord> getByRecordId(String id,Pageable page){
        return this.repo.findByRecordId(id, page);
    }
    
    @Override
    public Page<AuditRecord> getByRecordType(RecordType type,Pageable page){
        return this.repo.findByRecordType(type, page);
    }
    
    @Override
    public Page<AuditRecord> findByActionType(ActionType type,Pageable page){
        return this.repo.findByActionType(type, page);
    }
}

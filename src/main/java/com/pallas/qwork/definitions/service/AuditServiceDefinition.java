package com.pallas.qwork.definitions.service;

import com.pallas.qwork.definitions.Auditable;
import com.pallas.qwork.entities.AuditRecord;
import com.pallas.qwork.definitions.enums.ActionType;
import com.pallas.qwork.definitions.enums.RecordType;
import com.pallas.qwork.exceptions.RecordNotFoundException;
import java.time.LocalDateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface AuditServiceDefinition {
    
    void saveInfo(ActionType action,Auditable audit);
    
    void saveInfo(AuditRecord record);
    
    void delete(long id);
    
    AuditRecord getAuditRecord(long id) throws RecordNotFoundException;
    
    Page<AuditRecord> getAll(Pageable page);
    
    Page<AuditRecord> getByTimeBetween(LocalDateTime start, LocalDateTime end, Pageable page);
    
    Page<AuditRecord> getByAuthorFullnameLike(String fullName, Pageable page);
    
    Page<AuditRecord> getByRecordId(String id,Pageable page);
    
    Page<AuditRecord> getByRecordType(RecordType type,Pageable page);
    
    Page<AuditRecord> findByActionType(ActionType type,Pageable page);
}

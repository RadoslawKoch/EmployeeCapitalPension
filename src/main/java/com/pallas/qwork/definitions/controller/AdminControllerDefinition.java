package com.pallas.qwork.definitions.controller;

import com.pallas.qwork.definitions.enums.ActionType;
import com.pallas.qwork.definitions.enums.RecordType;
import com.pallas.qwork.entities.AuditRecord;
import com.pallas.qwork.exceptions.RecordNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdminControllerDefinition {
    
    String getLogContent();
    
    void cleanLog();
    
    Page<AuditRecord> getAuditRecords(Pageable page);
    
    void saveAuditRecord(AuditRecord record);

    void deleteAuditRecord(long id);
    
    AuditRecord getAuditRecord(long id) throws RecordNotFoundException;
    
    Page<AuditRecord> getAuditRecords(String start, String end,Pageable page);
    
    Page<AuditRecord> getAuditRecords(String name,Pageable page);
    
    Page<AuditRecord> getAuditRecordsById(String id,Pageable page);
    
    Page<AuditRecord> getAuditRecordsByType(RecordType type,Pageable page);
    
    Page<AuditRecord> getAuditRecordsByActionType(ActionType type,Pageable page);
}

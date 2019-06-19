package com.pallas.qwork.repo;

import com.pallas.qwork.definitions.enums.ActionType;
import com.pallas.qwork.entities.AuditRecord;
import com.pallas.qwork.definitions.enums.RecordType;
import java.time.LocalDateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditRepository extends PagingAndSortingRepository<AuditRecord,Long>{
    Page<AuditRecord> findByTimeBetween(LocalDateTime start, LocalDateTime end, Pageable page);
    Page<AuditRecord> findByAuthorFullnameLike(String fullName, Pageable page);
    Page<AuditRecord> findByRecordId(String id,Pageable page);
    Page<AuditRecord> findByRecordType(RecordType type,Pageable page);
    Page<AuditRecord> findByActionType(ActionType type,Pageable page);
}

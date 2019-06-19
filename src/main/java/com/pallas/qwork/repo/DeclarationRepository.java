package com.pallas.qwork.repo;

import com.pallas.qwork.entities.Declaration;
import java.time.LocalDateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeclarationRepository 
    extends PagingAndSortingRepository<Declaration,Long>{
    
    Page<Declaration> findByAccountId(String account,Pageable page);
    Page<Declaration> findByAccountIdAndDecision(String account,boolean decision,Pageable page);
    Page<Declaration> findByAccountIdAndTimeBetween(String account, LocalDateTime start,LocalDateTime end,Pageable page);
    
}

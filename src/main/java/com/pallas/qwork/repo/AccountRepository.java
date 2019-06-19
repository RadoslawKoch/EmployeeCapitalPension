package com.pallas.qwork.repo;

import com.pallas.qwork.entities.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository 
    extends PagingAndSortingRepository<Account,String> {
    Page<Account> findByActive(boolean isActive,Pageable page);
}

package com.pallas.qwork.definitions.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommunControllerDefinition<INFO,ID,NEW> {
    
    void add(NEW dto) throws Exception;
    
    void delete(ID id) throws Exception;

    INFO get(ID id) throws Exception;

    Page<INFO> get(Pageable page) throws Exception;
    
}

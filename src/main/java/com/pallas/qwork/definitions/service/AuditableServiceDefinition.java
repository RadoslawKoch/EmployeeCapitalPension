package com.pallas.qwork.definitions.service;

import com.pallas.qwork.definitions.Auditable;
import com.pallas.qwork.definitions.enums.ActionType;
import org.springframework.beans.factory.annotation.Autowired;

public class AuditableServiceDefinition {
    
    @Autowired
    protected AuditServiceDefinition audit;
    
    protected void loginfo(ActionType type, Auditable ad){
        this.audit.saveInfo(type, ad);
    }
}

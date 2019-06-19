package com.pallas.qwork.definitions.mappers;

import com.pallas.qwork.entities.Account;

public interface MapperDefinition<BASE,INFO,NEW> {
    
    BASE map(NEW dto, Account old);
    
    INFO map(BASE account);
}

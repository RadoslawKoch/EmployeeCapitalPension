package com.pallas.qwork.definitions;

import com.pallas.qwork.definitions.enums.RecordType;

public interface Auditable<I> {
    
    I getId();
    RecordType getRecordType();
    String getDescription();
}

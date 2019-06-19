package com.pallas.qwork.definitions.controller;

public interface EditableControllerDefinition<NEW,ID> {
    
    void put(NEW dto, ID id) throws Exception;
}

package com.pallas.qwork.definitions.error;


public interface ErrorLoggerDefinition {
    
    void write(Exception exc);
    
    String getLogContent();
    
    void cleanUp();
}

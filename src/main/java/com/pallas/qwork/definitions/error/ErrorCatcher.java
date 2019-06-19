package com.pallas.qwork.definitions.error;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.pallas.qwork.exceptions.AccountNotFoundException;
import com.pallas.qwork.exceptions.DeclarationNotFoundException;
import com.pallas.qwork.exceptions.DuplicateAccountException;
import com.pallas.qwork.exceptions.RecordNotFoundException;
import com.pallas.qwork.exceptions.TransactionNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


public abstract class ErrorCatcher {
    
    @Autowired
    protected ErrorLoggerDefinition logger;
    
    @ExceptionHandler({ 
	  AccountNotFoundException.class, 
	  DeclarationNotFoundException.class, 
	  DuplicateAccountException.class,
	  InvalidFormatException.class,
	  NullPointerException.class,
	  InvalidFormatException.class,
	  TransactionNotFoundException.class,
          RecordNotFoundException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleException(Exception ex) {
        this.logger.write(ex);
        return ex.getMessage();
    }	
    
}

package com.pallas.qwork.exceptions;

public class TransactionNotFoundException 
    extends Exception{

    public TransactionNotFoundException() {
        super("Transakcja o podanych parametrach nie została znaleziona.");
    }
}

package com.pallas.qwork.exceptions;

public class RecordNotFoundException extends Exception{

    public RecordNotFoundException() {
        super("Rekord o podanym numerze ID nie został znaleziony.");
    }
    
    
    
}

package com.pallas.qwork.exceptions;


public class DeclarationNotFoundException 
    extends Exception{

    public DeclarationNotFoundException() {
        super("Deklaracja o podanych parametrach nie została znaleziona.");
    }
}

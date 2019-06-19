package com.pallas.qwork.exceptions;


public class DuplicateAccountException 
    extends Exception{

    public DuplicateAccountException() {
        super("Konto o podanym numerze identyfikacyjnym już instnieje.");
    }
}

package com.pallas.qwork.exceptions;


public class AccountNotFoundException 
    extends Exception{

    public AccountNotFoundException() {
        super("Konto o podanym numerze identyfikacyjnym nie zostało znalezione.");
    }
}

package com.pallas.qwork.dto;

import java.math.BigDecimal;

public class AccountInfoDto {
    
    private String id;
    private BigDecimal balance;
    private boolean status;
    private String email;
    private String managerId;
    private DeclarationInfoDto lastDeclaration;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public DeclarationInfoDto getLastDeclaration() {
        return lastDeclaration;
    }

    public void setLastDeclaration(DeclarationInfoDto lastDeclaration) {
        this.lastDeclaration = lastDeclaration;
    }  

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }
    
    
}

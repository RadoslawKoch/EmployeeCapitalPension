package com.pallas.qwork.dto;

import java.math.BigDecimal;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class NewAccountDto {
    
    @NotNull
    @Size(min=3)
    private String id;
    
    @NotNull
    @Min(0)
    private BigDecimal balance;
    
    @NotNull
    private boolean active;
    
    @Email
    private String email;
    
    @Size(min=3)
    private String managerId;

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }  

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

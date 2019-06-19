package com.pallas.qwork.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pallas.qwork.definitions.Auditable;
import com.pallas.qwork.definitions.enums.RecordType;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="accounts")
public class Account 
        implements Serializable,
        Auditable<String>{
    
    @Id
    private String id;
    
    @Column(name="balance")
    private BigDecimal balance;   
        
    @Column(name="is_active")
    private boolean active;
    
    @Column(name="email")
    private String email;
    
    @Column(name="manager_id")
    private String managerId;
    
    @OneToMany(mappedBy="account",fetch=FetchType.LAZY)
    @JsonIgnore
    private List<Transaction> transactions = new ArrayList();
    
    @OneToMany(mappedBy="account",fetch=FetchType.LAZY)
    @JsonIgnore
    private List<Declaration> declarations = new ArrayList();

    public Account() {
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
    
    public Account(String accountId, boolean active) {
        this.id = accountId;
        this.balance = BigDecimal.ZERO;
        this.active = active;    
    } 
    
    public Account(String accountId,String email,String managerId,BigDecimal balance, boolean active) {
        this.id = accountId;
        this.balance = balance;
        this.active = active;
        this.managerId = managerId;
        this.email = email;
        
    } 

    public void setId(String accountId) {
        this.id = accountId;
    }

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

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<Declaration> getDeclarations() {
        return declarations;
    }

    public void setDeclarations(List<Declaration> declarations) {
        this.declarations = declarations;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public RecordType getRecordType() {
        return RecordType.ACCOUNT;
    }

    @Override
    public String getDescription() {
        return this.toString();
    }

    @Override
    public String toString() {
        return "Account{" + "id=" + id + ", balance=" + balance + ", active=" + active + ", email=" + email + '}';
    } 
}

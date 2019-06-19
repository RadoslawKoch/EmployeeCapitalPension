package com.pallas.qwork.entities;

import com.pallas.qwork.definitions.Auditable;
import com.pallas.qwork.definitions.enums.RecordType;
import com.pallas.qwork.definitions.enums.TransactionType;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="transactions")
public class Transaction 
    implements Serializable,
    Auditable<Long>{
    
    @Id
    @GeneratedValue
    private long id;
    
    @Column(name="time")
    private LocalDateTime time;
    
    @Column(name="title")
    private String title;
    
    @Column(name="amount")
    private BigDecimal amount;
    
    @ManyToOne
    @JoinColumn(name="account_id")
    private Account account;
    
    @Column(name="type")
    private TransactionType type;

    @Override
    public Long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }   

    @Override
    public RecordType getRecordType() {
        return RecordType.TRANSACTION;
    }

    @Override
    public String getDescription() {
        return this.toString();
    }

    @Override
    public String toString() {
        return "Transaction{" + "id=" + id + ", time=" + time + ", title=" + title + ", amount=" + amount + ", account=" + account + ", type=" + type + '}';
    }   
}

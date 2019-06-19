package com.pallas.qwork.dto;

import com.pallas.qwork.definitions.enums.TransactionType;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionInfoDto {

    private long id;
    private LocalDateTime time;
    private String title;
    private BigDecimal amount;
    private String accountId;
    private TransactionType type;

    public long getId() {
        return id;
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

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    } 
}

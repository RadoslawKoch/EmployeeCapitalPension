package com.pallas.qwork.dto;

import com.pallas.qwork.definitions.enums.TransactionType;
import java.math.BigDecimal;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class NewTransactionDto {

    
    @NotNull
    @Size(min=3)
    private String title;
    
    @Min(0)
    private BigDecimal amount;
    
    @NotNull
    @Size(min=3)
    private String accountId;
    
    @NotNull
    private TransactionType type;

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

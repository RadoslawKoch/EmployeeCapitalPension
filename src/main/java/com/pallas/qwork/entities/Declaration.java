package com.pallas.qwork.entities;

import com.pallas.qwork.definitions.Auditable;
import com.pallas.qwork.definitions.enums.RecordType;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="declarations")
public class Declaration 
    implements Serializable,
    Auditable<Long>{
    
    @Id
    @GeneratedValue
    private long id;
    
    @Column(name="content")
    private String content;
    
    @Column(name="decision")
    private boolean decision;
    
    @Column(name="time")
    private LocalDateTime time;
    
    @ManyToOne
    @JoinColumn(name="account_id")
    private Account account;

    @Override
    public Long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isDecision() {
        return decision;
    }

    public void setDecision(boolean decision) {
        this.decision = decision;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }   

    @Override
    public RecordType getRecordType() {
        return RecordType.DECLARATION;
    }

    @Override
    public String getDescription() {
        return this.toString();
    }

    @Override
    public String toString() {
        return "Declaration{" + "id=" + id + ", content=" + content + ", decision=" + decision + ", time=" + time + ", account=" + account + '}';
    }
}

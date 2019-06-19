package com.pallas.qwork.entities;

import com.pallas.qwork.definitions.Auditable;
import com.pallas.qwork.definitions.enums.ActionType;
import com.pallas.qwork.definitions.enums.RecordType;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="audit_records")
public class AuditRecord 
    implements Serializable{
    
    @Id
    @GeneratedValue
    private long id;
    
    @Column(name="record_type")
    private RecordType recordType;
    
    @Column(name="action_type")
    private ActionType actionType;
    
    @Column(name="description")
    private String description;
    
    @Column(name="author_fullname")
    private String authorFullname;
    
    @Column(name="author_id")
    private String authorId;
    
    @Column(name="time")
    private LocalDateTime time;
    
    @Column(name="record_id")
    private String recordId;

    public AuditRecord() {}
    
    public AuditRecord(ActionType actionType, String authorFullname, String authorId, Auditable audit) {
        this.recordType = audit.getRecordType();
        this.actionType = actionType;
        this.description =audit.getDescription();
        this.authorFullname = authorFullname;
        this.authorId = authorId;
        this.time = LocalDateTime.now();
        this.recordId = audit.getId().toString();
    } 

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public RecordType getRecordType() {
        return recordType;
    }

    public void setRecordType(RecordType recordType) {
        this.recordType = recordType;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthorFullname() {
        return authorFullname;
    }

    public void setAuthorFullname(String authorFullname) {
        this.authorFullname = authorFullname;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    } 
}

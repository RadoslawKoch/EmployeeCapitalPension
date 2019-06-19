package com.pallas.qwork.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class NewDeclarationDto {
    
    @NotNull
    @Size(min=3)
    private String userId;
    
    @NotNull
    @Size(min=3)
    private String content;
    
    @NotNull
    private boolean decision;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean getDecision() {
        return decision;
    }

    public void setDecision(boolean decision) {
        this.decision = decision;
    } 
}

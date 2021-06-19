package com.bercut.pltest.mashup.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class Search {
    private String projects;
    private String emails;
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private Date dateFrom;
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private Date dateUntil;
    private boolean isModified;
    private boolean onlyAutomated;

    public long getModifiedDataUntil() {
        return dateUntil.getTime() + 86400000;
    }

    public boolean getIsModified() {
        return isModified;
    }

    public void setIsModified(boolean modified) {
        isModified = modified;
    }

    public boolean getOnlyAutomated() {
        return onlyAutomated;
    }

    public void setOnlyAutomated(boolean onlyAutomated) {
        this.onlyAutomated = onlyAutomated;
    }
}

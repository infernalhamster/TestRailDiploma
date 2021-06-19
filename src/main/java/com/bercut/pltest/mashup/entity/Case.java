package com.bercut.pltest.mashup.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Case {
    @JsonProperty("created_by")
    private int createdBy;
    private String emailCreatedBy;
    @JsonProperty("created_on")
    private Timestamp createdOn;
    private String estimate;
    @JsonProperty("estimate_forecast")
    private String estimateForecast;
    private int id;
    @JsonProperty("milestone_id")
    private int milestoneId;
    @JsonProperty("priority_id")
    private int priorityId;
    private String refs;
    @JsonProperty("suite_id")
    private int suiteId;
    private String title;
    @JsonProperty("type_id")
    private int typeId;
    private String type;
    @JsonProperty("updated_by")
    private int updatedBy;
    private String emailUpdatedBy;
    @JsonProperty("updated_on")
    private Timestamp updatedOn;

    public Date getCreatedOn() {
        return new Date(createdOn.getTime() * 1000);
    }

    public Date getUpdatedOn() {
        return new Date(updatedOn.getTime() * 1000);
    }
}

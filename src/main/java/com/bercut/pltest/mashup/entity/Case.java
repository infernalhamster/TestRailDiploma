package com.bercut.pltest.mashup.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Case {
    @JsonProperty("created_by")
    private int createdBy;
    @JsonProperty("created_on")
    private Timestamp createdOn;
    private String estimate;
    @JsonProperty("estimate_forecast")
    private String estimateForecast;
    private int id;
    @JsonProperty("milestone_id")
    private int milestoneId;
    @JsonProperty("priority_id")
    private String priorityId;
    private String refs;
    @JsonProperty("suite_id")
    private String suiteId;
    private String title;
    @JsonProperty("type_id")
    private String typeId;
    @JsonProperty("updated_by")
    private int updatedBy;
    @JsonProperty("updated_on")
    private Timestamp updatedOn;

    public Date getCreatedOn() {
        return new Date(createdOn.getTime() * 1000);
    }

    public Date getUpdatedOn() {
        return new Date(updatedOn.getTime() * 1000);
    }
}

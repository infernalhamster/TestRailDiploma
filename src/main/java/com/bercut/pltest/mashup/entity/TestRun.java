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
public class TestRun {
    @JsonProperty("assignedto_id")
    private int assignedtoId;
    @JsonProperty("blocked_count")
    private int blockedCount;
    @JsonProperty("completed_on")
    private int completedOn;
    @JsonProperty("config")
    private String config;
    @JsonProperty("created_by")
    private int createdBy;
    @JsonProperty("created_on")
    private Timestamp createdOn;
    @JsonProperty("description")
    private String description;
    @JsonProperty("failed_count")
    private int failedCount;
    @JsonProperty("id")
    private int id;
    @JsonProperty("include_all")
    private boolean includeAll;
    @JsonProperty("is_completed")
    private boolean isCompleted;
    @JsonProperty("milestone_id")
    private int milestoneId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("passed_count")
    private int passedCount;
    @JsonProperty("plan_id")
    private int planId;
    @JsonProperty("project_id")
    private int projectId;
    @JsonProperty("retest_count")
    private int retestCount;
    @JsonProperty("suite_id")
    private int suiteId;
    @JsonProperty("untested_count")
    private int untestedCount;
    @JsonProperty("updated_on")
    private int updatedOn;
    @JsonProperty("url")
    private String url;

    public Date getCreatedOn() {
        return new Date(createdOn.getTime() * 1000);
    }
}

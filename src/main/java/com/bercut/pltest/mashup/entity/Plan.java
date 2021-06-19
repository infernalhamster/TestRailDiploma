package com.bercut.pltest.mashup.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Plan {
    @JsonProperty("assignedto_id")
    private int assignedtoId;
    @JsonProperty("blocked_count")
    private int blockedCount;
    @JsonProperty("completed_on")
    private int completedOn;
    @JsonProperty("created_by")
    private int createdBy;
    @JsonProperty("created_on")
    private int createdOn;
    @JsonProperty("description")
    private String description;
    @JsonProperty("entries")
    private Entries[] entries;
    @JsonProperty("failed_count")
    private int failedCount;
    @JsonProperty("id")
    private int id;
    @JsonProperty("is_completed")
    private boolean isCompleted;
    @JsonProperty("milestone_id")
    private int milestoneId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("passed_count")
    private int passedCount;
    @JsonProperty("project_id")
    private int projectId;
    @JsonProperty("retest_count")
    private int retestCount;
    @JsonProperty("untested_count")
    private int untestedCount;
    @JsonProperty("url")
    private String url;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Entries {
        @JsonProperty("runs")
        private TestRun[] runs;
    }
}

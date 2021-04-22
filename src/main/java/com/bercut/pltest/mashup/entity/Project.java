package com.bercut.pltest.mashup.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Project {

    private String announcement;
    @JsonProperty("completed_on")
    private int completedOn;
    private int id;
    @JsonProperty("isCompleted")
    private boolean is_completed;
    private String name;
    @JsonProperty("show_announcement")
    private boolean showAnnouncement;
    private String url;
}

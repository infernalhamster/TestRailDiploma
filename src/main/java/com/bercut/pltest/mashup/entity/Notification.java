package com.bercut.pltest.mashup.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@JsonAutoDetect
public class Notification {

    @JsonProperty("@type")
    private final String type = "MessageCard";
    @JsonProperty("@context")
    private final String context = "https://schema.org/extensions";
    private final String summary = "Summary";
    private final String title = "Automatic notification";
    private List<Section> sections;

    public Notification(List<Section> sections) {
        this.sections = sections;
    }

    @Data
    @JsonAutoDetect
    @AllArgsConstructor
    public static class Section {
        private String text;
        private List<Facts> facts;

        @Data
        @JsonAutoDetect
        @AllArgsConstructor
        public static class Facts {
            private String name;
            private String value;
        }
    }
}

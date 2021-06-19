package com.bercut.pltest.mashup.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
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

    @Getter
    @Setter
    @JsonAutoDetect
    public static class Section {
        private String text;
        private final boolean wrap = true;

        public Section(String text) {
            this.text = text;
        }

        @Setter
        @Getter
        @JsonAutoDetect
        public static class Facts {
            private String name;
            private String value;

            public Facts(String name, String value) {
                this.name = name;
                this.value = value;
            }
        }
    }
}

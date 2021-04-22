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
public class User {

    private String email;
    private int id;
    @JsonProperty("is_active")
    private boolean isActive;
    private String name;
    @JsonProperty("role_id")
    private String roleId;
    private String role;

}

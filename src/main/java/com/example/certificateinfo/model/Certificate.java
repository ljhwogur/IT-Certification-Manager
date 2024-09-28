package com.example.certificateinfo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Certificate {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    // 기본 생성자
    public Certificate() {}

    // 매개변수 생성자
    public Certificate(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // Getter 및 Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

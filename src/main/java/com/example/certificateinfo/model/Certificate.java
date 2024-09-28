package com.example.certificateinfo.model;

public class Certificate {
    private String name;
    private String description;

    // 생성자
    public Certificate(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // 게터와 세터
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
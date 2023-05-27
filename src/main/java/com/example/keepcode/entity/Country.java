package com.example.keepcode.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Country {

    @JsonProperty(value = "country")
    private Integer id;
    @JsonProperty(value = "country_text")
    private String name;

    public Country() {
    }

    public Country(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

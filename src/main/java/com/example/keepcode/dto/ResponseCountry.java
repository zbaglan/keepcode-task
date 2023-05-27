package com.example.keepcode.dto;

import com.example.keepcode.entity.Country;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ResponseCountry {
    @JsonProperty(value = "response")
    private Integer code;
    @JsonProperty(value = "countries")
    private List<Country> countries;

    public ResponseCountry() {
    }

    public ResponseCountry(Integer code, List<Country> countries) {
        this.code = code;
        this.countries = countries;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}

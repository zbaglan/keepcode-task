package com.example.keepcode.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NumberDetail {
    @JsonProperty(value = "number")
    private String number;
    @JsonProperty(value = "country")
    private Integer country;
    @JsonProperty(value = "updated_at")
    private String updatedAt;
    @JsonProperty(value = "data_humans")
    private String dataHumans;
    @JsonProperty(value = "full_number")
    private String fullNumber;
    @JsonProperty(value = "country_text")
    private String countryText;
    @JsonProperty(value = "maxdate")
    private String maxDate;
    @JsonProperty(value = "status")
    private String status;

    public NumberDetail() {
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getCountry() {
        return country;
    }

    public void setCountry(Integer country) {
        this.country = country;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setMaxDate(String maxDate) {
        this.maxDate = maxDate;
    }

    public String getDataHumans() {
        return dataHumans;
    }

    public void setDataHumans(String dataHumans) {
        this.dataHumans = dataHumans;
    }

    public String getFullNumber() {
        return fullNumber;
    }

    public void setFullNumber(String fullNumber) {
        this.fullNumber = fullNumber;
    }

    public String getCountryText() {
        return countryText;
    }

    public void setCountryText(String countryText) {
        this.countryText = countryText;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

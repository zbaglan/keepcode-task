package com.example.keepcode.dto;

import com.example.keepcode.entity.NumberDetail;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ResponseNumberDetail {
    @JsonProperty(value = "response")
    private Integer code;
    @JsonProperty(value = "numbers")
    private List<NumberDetail> numbers;

    public ResponseNumberDetail() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public List<NumberDetail> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<NumberDetail> numbers) {
        this.numbers = numbers;
    }
}

package com.example.keepcode.entity;

public class PriceDetail {
    private String name;
    private String price;

    public PriceDetail() {
    }

    public PriceDetail(String name, String price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}

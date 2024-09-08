package com.example.trainticket.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Train {

    private String from;
    private String to;
    private double price;

    private List<Section> sections;


    public Train(String from, String to, double price) {
        this.from = from;
        this.to = to;
        this.price = price;
        this.sections = Arrays.asList(
            new Section("Section_A"),
            new Section("Section_B")
        );
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }
}



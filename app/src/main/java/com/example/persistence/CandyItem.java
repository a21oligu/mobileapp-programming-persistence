package com.example.persistence;

public class CandyItem {

    private String name;
    private String taste;
    private String color;

    public CandyItem(String name, String taste, String color) {
        this.name = name;
        this.taste = taste;
        this.color = color;
    }

    @Override
    public String toString() {
        return String.format("{ name=\"%s\", taste=\"%s\", color=\"%s\" }", this.name, this.taste, this.color);
    }
}

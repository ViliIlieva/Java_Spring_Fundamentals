package com.example.shoppinglist.model.entity;

public enum CategoryEnum {
    FOOD("Food"),
    DRINK("Drink"),
    HOUSEHOLD("Houshold"),
    OTHER("other");

    private final String value;
    private CategoryEnum(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}

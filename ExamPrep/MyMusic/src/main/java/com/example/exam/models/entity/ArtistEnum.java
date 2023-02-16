package com.example.exam.models.entity;

public enum ArtistEnum {
    QUEEN ("Queen"),
    METALLICA ("Metallica"),
    MADONNA ("Madonna");


    private final String value;

    private ArtistEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

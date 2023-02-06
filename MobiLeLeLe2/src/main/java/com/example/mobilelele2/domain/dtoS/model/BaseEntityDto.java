package com.example.mobilelele2.domain.dtoS.model;

public abstract class BaseEntityDto {

    private String id;

    public BaseEntityDto() {
    }

    public String getId() {
        return id;
    }

    public BaseEntityDto setId(String id) {
        this.id = id;
        return this;
    }

}

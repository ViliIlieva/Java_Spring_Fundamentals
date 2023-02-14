package com.example.shoppinglist.model.entity;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity{
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private CategoryEnum name;

    @Column
    private String description;

    public Category() {
    }

    public Category(CategoryEnum name) {
        this.name = name;
    }

    public CategoryEnum getName() {
        return name;
    }

    public void setName(CategoryEnum name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}

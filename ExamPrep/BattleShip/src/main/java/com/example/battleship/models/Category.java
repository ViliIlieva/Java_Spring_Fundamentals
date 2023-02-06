package com.example.battleship.models;

import jakarta.persistence.*;

@Table(name = "categories")
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated
    @Column(unique = true, nullable = false)
    private ShipType name;

    @Column(columnDefinition = "text")
    private String description;

    public Category() {
    }

    public Category(ShipType name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public Category setId(long id) {
        this.id = id;
        return this;
    }

    public ShipType getName() {
        return name;
    }

    public Category setName(ShipType name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Category setDescription(String description) {
        this.description = description;
        return this;
    }
}

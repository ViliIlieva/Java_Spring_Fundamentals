package com.example.battleship.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Table(name = "ships")
@Entity
public class Ship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    //малкия long по подразбиране е nullable false
    private long health;

    private long power;

    @Column(nullable = false)
    private LocalDate created;

    //TODO: Category

    @ManyToOne
    private User owner;

    public Ship() {
    }


}

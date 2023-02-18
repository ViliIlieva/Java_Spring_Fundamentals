package com.example.exam.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "offers")
public class Offer extends BaseEntity{

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    @ManyToOne
    private User creator;

    @ManyToOne
    private Condition condition;

}

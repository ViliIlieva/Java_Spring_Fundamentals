package com.example.exam.models.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "albums")
public class Album  extends BaseEntity{

    @Column(nullable = false)
    private String name;

    @Column(name = "image_url", nullable = false)
    private String imgUrl;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private int copies;

    @Column(nullable = false, columnDefinition = "decimal(19,2)")
    private BigDecimal price;

    @Column(nullable = false, name = "release_date")
    private LocalDate releaseDate;

    private String producer;

    @Column(nullable = false)
    private GenreEnum genre;

    @ManyToOne
    private User addedFrom;

    @ManyToOne
    private Artist artist;
}

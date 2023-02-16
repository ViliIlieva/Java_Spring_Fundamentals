package com.example.exam.models.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "artists")
public class Artist extends BaseEntity{

    @Enumerated(EnumType.STRING)
    private ArtistEnum name;

    @Column(name = "career_information", columnDefinition = "TEXT")
    private String careerInformation;

    public Artist(ArtistEnum name) {
        this.name = name;
    }
}

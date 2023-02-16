package com.example.exam.models.dtos.viewModels;

import com.example.exam.models.entity.Artist;
import com.example.exam.models.entity.GenreEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class AllAlbumDTO {
    private Long id;
    private String name;
    private Artist artist;
    private GenreEnum genre;
    private BigDecimal price;
    private LocalDate releaseDate;
    private Integer copies;
    private String imgUrl;
}

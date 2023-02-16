package com.example.exam.models.dtos.bindingModels;

import com.example.exam.models.entity.Artist;
import com.example.exam.models.entity.ArtistEnum;
import com.example.exam.models.entity.GenreEnum;
import com.example.exam.models.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class AddAlbumDTO {

    private Long id;

    @Size(min = 3, max = 20)
    @NotBlank
    private String name;

    @Size(min = 5)
    @NotBlank
    private String imgUrl;

    @NotNull
    @Positive
    private BigDecimal price;

    @NotNull
    @Min (10)
    private int copies;

    @NotNull
    @PastOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;

    private String producer;

    @NotNull
    private ArtistEnum artist;

    @NotNull
    private GenreEnum genre;

    @NotBlank
    @Size(min = 5)
    private String description;
}







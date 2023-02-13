package com.example.shoppinglist.model.dtos;

import com.example.shoppinglist.model.entity.CategoryEnum;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AddProductDTO {
    private Long id;

    @Size(min = 3, max = 20)
    @NotBlank
    private String name;

    @Size(min = 5)
    @NotBlank
    private String description;

    @FutureOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd г. hh:mm")
    private LocalDateTime neededBefore;

    @Positive
    @NotNull
    private int price;

    @NotNull
    private CategoryEnum category;

    public AddProductDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getNeededBefore() {
        return neededBefore;
    }

    public void setNeededBefore(LocalDateTime neededBefore) {
        this.neededBefore = neededBefore;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryEnum category) {
        this.category = category;
    }
}

package com.example.coffee_shop.models.dtos.bindingModels;

import com.example.coffee_shop.models.entity.CategoryEnum;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class AddOrderDTO {
    private Long id;

    @NotBlank
    @Size(min = 3, max = 20)
    private String name;

    @NotNull
    @DecimalMin("1")
    private BigDecimal price;

    @NotNull
    @DateTimeFormat(pattern = "mm/dd/yyyy --:-- --")
    @PastOrPresent(message = "Order time can not be in the future")
    private LocalDateTime orderTime;

    @NotNull
    private CategoryEnum category;

    @NotBlank
    @Size(min = 5)
    private String description;


}

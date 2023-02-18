package com.example.exam.models.dtos.bindingModels;

import com.example.exam.models.Enum.ConditionEnum;
import com.example.exam.models.entity.Condition;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class AddOfferDTO {

    private Long id;

    @NotBlank
    @Size(min = 2, max = 50)
    private String description;

    @NotNull
    @Positive
    private BigDecimal price;

    @NotNull
    private ConditionEnum condition;
}

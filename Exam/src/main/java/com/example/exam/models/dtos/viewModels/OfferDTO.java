package com.example.exam.models.dtos.viewModels;

import com.example.exam.models.Enum.ConditionEnum;
import com.example.exam.models.entity.Condition;
import com.example.exam.models.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class OfferDTO {

    private Long id;

    private ConditionEnum condition;

    private BigDecimal price;

    private String description;

    private User creator;
}

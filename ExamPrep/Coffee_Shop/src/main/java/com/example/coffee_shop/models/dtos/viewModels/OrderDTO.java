package com.example.coffee_shop.models.dtos.viewModels;

import com.example.coffee_shop.models.entity.Category;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class OrderDTO {
    private Long id;
    private String name;
    private BigDecimal price;
    private Category category;
}

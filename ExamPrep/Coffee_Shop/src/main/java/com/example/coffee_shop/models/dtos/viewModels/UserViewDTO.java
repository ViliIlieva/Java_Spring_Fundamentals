package com.example.coffee_shop.models.dtos.viewModels;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserViewDTO {
    private String username;
    private Integer countOfOrders;
}

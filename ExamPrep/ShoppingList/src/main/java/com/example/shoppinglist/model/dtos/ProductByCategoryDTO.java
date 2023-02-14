package com.example.shoppinglist.model.dtos;

import java.util.Set;

public class ProductByCategoryDTO {

    private Set<ProductDTO> foodProducts;
    private Set<ProductDTO> drinkProducts;
    private Set<ProductDTO> householdProducts;
    private Set<ProductDTO> otherProducts;

    public ProductByCategoryDTO() {
    }

    public Set<ProductDTO> getFoodProducts() {
        return foodProducts;
    }

    public void setFoodProducts(Set<ProductDTO> foodProducts) {
        this.foodProducts = foodProducts;
    }

    public Set<ProductDTO> getDrinkProducts() {
        return drinkProducts;
    }

    public void setDrinkProducts(Set<ProductDTO> drinkProducts) {
        this.drinkProducts = drinkProducts;
    }

    public Set<ProductDTO> getHouseholdProducts() {
        return householdProducts;
    }

    public void setHouseholdProducts(Set<ProductDTO> householdProducts) {
        this.householdProducts = householdProducts;
    }

    public Set<ProductDTO> getOtherProducts() {
        return otherProducts;
    }

    public void setOtherProducts(Set<ProductDTO> otherProducts) {
        this.otherProducts = otherProducts;
    }
}

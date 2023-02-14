package com.example.shoppinglist.service;

import com.example.shoppinglist.model.dtos.ProductByCategoryDTO;
import com.example.shoppinglist.model.dtos.ProductDTO;
import com.example.shoppinglist.model.entity.Category;
import com.example.shoppinglist.model.entity.CategoryEnum;
import com.example.shoppinglist.model.entity.Product;
import com.example.shoppinglist.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class HomeService {

    private final ProductService productService;
    private final AuthService authService;
    private final CategoryRepository categoryRepository;

    @Autowired
    public HomeService(ProductService productService,
                       AuthService authService,
                       CategoryRepository categoryRepository) {
        this.productService = productService;
        this.authService = authService;
        this.categoryRepository = categoryRepository;
    }

    public ProductByCategoryDTO getProducts() {
        ProductByCategoryDTO products = new ProductByCategoryDTO();
        products.setFoodProducts(this.getProductsByCategory(this.categoryRepository.findByName(CategoryEnum.FOOD)));
        products.setDrinkProducts(this.getProductsByCategory(this.categoryRepository.findByName(CategoryEnum.DRINK)));
        products.setHouseholdProducts(this.getProductsByCategory(this.categoryRepository.findByName(CategoryEnum.HOUSEHOLD)));
        products.setOtherProducts(this.getProductsByCategory(this.categoryRepository.findByName(CategoryEnum.OTHER)));
        return products;
    }

    private Set<ProductDTO> getProductsByCategory(Category category) {
        return this.productService.findProductByCategory(category);
    }

}

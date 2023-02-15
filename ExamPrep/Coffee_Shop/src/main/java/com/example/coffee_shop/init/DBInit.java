package com.example.coffee_shop.init;

import com.example.coffee_shop.models.entity.Category;
import com.example.coffee_shop.models.entity.CategoryName;
import com.example.coffee_shop.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DBInit implements CommandLineRunner {

    private final CategoryRepository categoryRepository;

    @Autowired
    public DBInit(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // метод който проверява дали имаме връзка с нашата база
    @Override
    public void run(String... args) throws Exception {

        if (this.categoryRepository.count() == 0) {
            Arrays.stream(CategoryName.values())
                    .forEach(categoryName -> {
                        Category category = new Category();
                        category.setName(categoryName);
                        switch (categoryName) {
                            case CAKE -> category.setNeededTime(10);
                            case DRINK -> category.setNeededTime(1);
                            case COFFEE -> category.setNeededTime(2);
                            case OTHER -> category.setNeededTime(5);
                        }
                        categoryRepository.save(category);
                    });
        }
//        System.out.println("seed");
    }
}

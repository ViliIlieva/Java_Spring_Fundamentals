package com.example.battleship.seeders;


import com.example.battleship.domain.entities.Category;
import com.example.battleship.domain.entities.ShipType;
import com.example.battleship.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategorySeeder implements CommandLineRunner {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategorySeeder(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    //задача 2 от изпита
    // метод който проверява дали имаме връзка с нашата база
    @Override
    public void run(String... args) throws Exception {
        //слагаме три категории според типа в енъм класа, прави го само при първоначално стартиране
        if (this.categoryRepository.count() == 0) {
            List<Category> categories = Arrays.stream(ShipType.values())
                .map(Category::new)
                .collect(Collectors.toList());

            this.categoryRepository.saveAll(categories);
        }
    }
}

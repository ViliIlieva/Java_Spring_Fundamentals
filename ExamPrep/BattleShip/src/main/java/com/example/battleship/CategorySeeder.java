package com.example.battleship;

import com.example.battleship.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CategorySeeder implements CommandLineRunner {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategorySeeder(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        //така проверяваме дали сме си надигнали базата и дали имаме достъп до нея
        //като класа трябва да е анотиран със @Component
        if(this.categoryRepository.count() == 0){
            System.out.println("seed");
        }
    }
}

package com.example.spotifyplaylistapp.seeders;

import com.example.spotifyplaylistapp.model.entity.Style;
import com.example.spotifyplaylistapp.model.entity.StyleEnum;
import com.example.spotifyplaylistapp.repository.StyleRepository;
import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategorySeeder implements CommandLineRunner {
    private final StyleRepository styleRepository;
    @Autowired
    public CategorySeeder(StyleRepository styleRepository) {
        this.styleRepository = styleRepository;
    }
    //задача 2 от изпита
    // метод който проверява дали имаме връзка с нашата база
    @Override
    public void run(String... args) throws Exception {
        //слагаме три стила според типа в енъм класа, прави го само при първоначално стартиране
        if (this.styleRepository.count() == 0) {
            List<Style> styles = Arrays.stream(StyleEnum.values())
                .map(Style::new)
                .collect(Collectors.toList());

            this.styleRepository.saveAll(styles);
        }
//        System.out.println ("seed");
    }
}

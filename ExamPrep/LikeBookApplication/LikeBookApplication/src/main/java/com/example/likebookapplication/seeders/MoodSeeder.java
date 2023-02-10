package com.example.likebookapplication.seeders;

import com.example.likebookapplication.model.entity.Mood;
import com.example.likebookapplication.model.entity.MoodsEnum;
import com.example.likebookapplication.repository.MoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MoodSeeder implements CommandLineRunner {

    private final MoodRepository moodRepository;

    @Autowired
    public MoodSeeder(MoodRepository moodRepository) {
        this.moodRepository = moodRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (this.moodRepository.count() == 0){
            List<Mood> moods = Arrays.stream(MoodsEnum.values())
                    .map(Mood::new).toList();

            this.moodRepository.saveAll(moods);
//            System.out.println("seed");
        }
    }
}

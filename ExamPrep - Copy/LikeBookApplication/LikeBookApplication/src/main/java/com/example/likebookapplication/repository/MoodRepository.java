package com.example.likebookapplication.repository;

import com.example.likebookapplication.model.entity.Mood;
import com.example.likebookapplication.model.entity.MoodsEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MoodRepository extends JpaRepository<Mood, Long> {


    Mood findByMoodName(MoodsEnum type);

}

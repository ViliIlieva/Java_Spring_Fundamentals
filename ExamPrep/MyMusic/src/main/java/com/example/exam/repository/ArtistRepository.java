package com.example.exam.repository;

import com.example.exam.models.entity.Artist;
import com.example.exam.models.entity.ArtistEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {
    Artist findByName(ArtistEnum artist);
}

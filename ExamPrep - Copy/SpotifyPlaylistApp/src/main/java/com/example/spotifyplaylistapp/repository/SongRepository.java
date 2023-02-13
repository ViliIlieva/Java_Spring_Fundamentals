package com.example.spotifyplaylistapp.repository;

import com.example.spotifyplaylistapp.model.dtos.SongDTO;
import com.example.spotifyplaylistapp.model.entity.Song;
import com.example.spotifyplaylistapp.model.entity.Style;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
    Set<Song> findByStyle(Style style);

    @Query("select  s, u from Song s join s.users u on u.id = :id")
    Set<Song> findAllByUserId(@Param ("id") Long id);


}

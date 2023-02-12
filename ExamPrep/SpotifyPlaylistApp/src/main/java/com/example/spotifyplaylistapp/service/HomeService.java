package com.example.spotifyplaylistapp.service;

import com.example.spotifyplaylistapp.model.dtos.SongDTO;
import com.example.spotifyplaylistapp.model.dtos.SongsByGenreDTO;
import com.example.spotifyplaylistapp.model.entity.Song;
import com.example.spotifyplaylistapp.model.entity.Style;
import com.example.spotifyplaylistapp.model.entity.StyleEnum;
import com.example.spotifyplaylistapp.repository.StyleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class HomeService {
    private final SongService songService;
    private final AuthService userService;
    private final StyleRepository styleRepository;

    @Autowired
    public HomeService(SongService songService,
                       AuthService userService, StyleRepository styleRepository) {
        this.songService = songService;
        this.userService = userService;
        this.styleRepository = styleRepository;
    }

    public SongsByGenreDTO getSongs(){
        SongsByGenreDTO songs = new SongsByGenreDTO ();
        songs.setPopSongs (this.getSongsByGenre (this.styleRepository.findByStyleName (StyleEnum.POP)));
        songs.setRockSongs (this.getSongsByGenre (this.styleRepository.findByStyleName (StyleEnum.ROCK)));
        songs.setJazzSongs (this.getSongsByGenre (this.styleRepository.findByStyleName (StyleEnum.JAZZ)));
        return songs;
    }

    public void addSong(Long songId, Long userId) {
        Song song = this.songService.findSongById(songId);
        this.userService.addSongToUser(userId, song);
    }

    public void removeSong(Long songId, Long userId) {
        this.userService.removeSongFromUser(userId, songId);
    }

    public void deleteAll(Long userId) {
        this.userService.deleteAllSongs(userId);
    }

    private Set<SongDTO> getSongsByGenre(Style style) {
        return this.songService.findSongsByStyle(style);
    }
}

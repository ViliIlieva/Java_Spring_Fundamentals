package com.example.spotifyplaylistapp.service;

import com.example.spotifyplaylistapp.model.dtos.AddSongDTO;
import com.example.spotifyplaylistapp.model.entity.Song;
import com.example.spotifyplaylistapp.model.entity.Style;
import com.example.spotifyplaylistapp.model.entity.StyleEnum;
import com.example.spotifyplaylistapp.model.entity.User;
import com.example.spotifyplaylistapp.repository.SongRepository;
import com.example.spotifyplaylistapp.repository.StyleRepository;
import com.example.spotifyplaylistapp.repository.UserRepository;
import com.example.spotifyplaylistapp.session.LoggedUser;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SongService {
    private final StyleRepository styleRepository;
    private final UserRepository userRepository;
    private final LoggedUser loggedUser;
    private final SongRepository songRepository;

    public SongService(StyleRepository styleRepository,
                       UserRepository userRepository,
                       LoggedUser loggedUser,
                       SongRepository songRepository) {
        this.styleRepository = styleRepository;
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
        this.songRepository = songRepository;
    }

    public boolean addSong(AddSongDTO addSongDTO){
        StyleEnum type = switch (addSongDTO.getStyle ().toString ().toUpperCase ()){
            case "POP" -> StyleEnum.POP;
            case "ROCK" -> StyleEnum.ROCK;
            case "JAZZ" -> StyleEnum.JAZZ;
            default -> StyleEnum.POP;
        };

        Style style = this.styleRepository.findByStyleName (type);

        Song song = new Song ();
        song.setPerformer (addSongDTO.getPerformer ());
        song.setTitle (addSongDTO.getTitle ());
        song.setReleaseDate (addSongDTO.getReleaseDate ());
        song.setDuration (addSongDTO.getDuration ());
        song.setStyle (style);

        this.songRepository.save (song);
        return true;
    }
}

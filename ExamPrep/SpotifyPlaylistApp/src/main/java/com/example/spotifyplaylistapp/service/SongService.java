package com.example.spotifyplaylistapp.service;

import com.example.spotifyplaylistapp.model.dtos.AddSongDTO;
import com.example.spotifyplaylistapp.model.dtos.SongDTO;
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
import java.util.Set;
import java.util.stream.Collectors;

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

    public boolean addSong(AddSongDTO addSongDTO) {

        StyleEnum type = switch (addSongDTO.getStyle ().toString ().toUpperCase ()) {
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

    public Song findSongById(Long songId) {
        return this.songRepository.findById (songId).orElseThrow ();
    }

    public Set<SongDTO> findSongsByStyle(Style style) {
        return this.songRepository.findByStyle (style)
                .stream ()
                .map (this::mapSongDTO)
                .collect (Collectors.toSet ());
    }

    public Set<SongDTO> getPlaylist(Long id) {
        Set<SongDTO> playlist = this.songRepository.findAllByUserId (id)
                .stream ()
                .map (this::mapSongDTO)
                .collect (Collectors.toSet ());

        return playlist;
    }

    public String sumOfDuration (Set<SongDTO> playlist){
        long sumOfDuration = 0L;
        for (SongDTO song : playlist) {
            sumOfDuration= sumOfDuration + song.getDuration ();
        }
        String durationInMinutes = String.format ("%d:%02d:%02d",
                sumOfDuration / 3600, (sumOfDuration % 3600) / 60, (sumOfDuration % 60));
        return durationInMinutes;
    }


    private SongDTO mapSongDTO(Song song) {
        SongDTO songDTO = new SongDTO ();
        songDTO.setId (song.getId ());
        songDTO.setDuration (song.getDuration ());
        songDTO.setPerformer (song.getPerformer ());
        songDTO.setTitle (song.getTitle ());
        return songDTO;
    }
}

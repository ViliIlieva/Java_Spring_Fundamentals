package com.example.spotifyplaylistapp.service;

import com.example.spotifyplaylistapp.model.dtos.LoginDTO;
import com.example.spotifyplaylistapp.model.dtos.UserRegistrationDTO;
import com.example.spotifyplaylistapp.model.entity.Song;
import com.example.spotifyplaylistapp.model.entity.User;
import com.example.spotifyplaylistapp.repository.UserRepository;
import com.example.spotifyplaylistapp.session.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final LoggedUser userSession;

    @Autowired
    public AuthService(UserRepository userRepository, LoggedUser loggedUser, LoggedUser userSession) {
        this.userRepository = userRepository;
        this.userSession = userSession;
    }

    public boolean isLoggedIn(){
        return this.userSession.getId ()>0;
    }

    public boolean register(UserRegistrationDTO registrationDTO) {
        //дали двете пароли съвпадат
        if (!registrationDTO.getPassword().equals(registrationDTO.getConfirmPassword())){
            return false;
        }
        //имейла дали вече го има в базата, защото трябва да е уникален
        Optional<User> byEmail = this.userRepository.findByEmail(registrationDTO.getEmail());
        if(byEmail.isPresent()){
            return false;
        }
        //дали юзъра вече го има в базата, защото трябва да е уникален
        Optional<User> byUsername = this.userRepository.findByUsername(registrationDTO.getUsername());
        if (byUsername.isPresent()) {
            return false;
        }

        User user = new User();
        user.setUsername(registrationDTO.getUsername());
        user.setEmail(registrationDTO.getEmail());
        user.setPassword(registrationDTO.getPassword());

        this.userRepository.save(user);

        return true;
    }

    public boolean login( LoginDTO loginDTO) {
        //създай ми юзър
        //открий по юзър нейм и парола, подадени от формата дали ги има в репозиторито
        Optional<User> user = this.userRepository
                .findByUsernameAndPassword(loginDTO.getUsername(), loginDTO.getPassword());

        if (user.isEmpty()) {//ако не сме открили такъв юзър в базата
            return false;
        }

        //ако има такъв го логни, като сетваме ID-то му и цялото му име и върни тру
        this.userSession.login(user.get());

        return true;
    }

    public void logout() {
        this.userSession.logout();
    }

    public long getLoggedUserId() {
        return this.userSession.getId();
    }

    public void addSongToUser(long userId, Song song) {
        User user = this.userRepository.findById (userId).orElseThrow ();
        if (user.getPlaylist().stream().noneMatch(s -> s.getId().equals(song.getId()))) {
            user.addSongToPlaylist(song);
            this.userRepository.save(user);
        }
    }

    public void removeSongFromUser(Long userId, Long songId) {
        User user = this.userRepository.findById (userId).orElseThrow ();
        user.removeSongFromPlaylist (songId);
        this.userRepository.save (user);
    }

    public void deleteAllSongs(Long userId) {
        User user = this.userRepository.findById (userId).orElseThrow ();
        user.deleteAllSongFromPlaylist ();
        this.userRepository.save (user);
    }
}

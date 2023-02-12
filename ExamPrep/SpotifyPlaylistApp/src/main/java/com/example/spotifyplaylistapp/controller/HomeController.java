package com.example.spotifyplaylistapp.controller;

import com.example.spotifyplaylistapp.model.dtos.SongsByGenreDTO;
import com.example.spotifyplaylistapp.service.AuthService;
import com.example.spotifyplaylistapp.service.HomeService;
import com.example.spotifyplaylistapp.service.SongService;
import com.example.spotifyplaylistapp.session.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {
    private final AuthService authService;
    private final HomeService homeService;
    private final LoggedUser loggedUser;
    private final SongService songService;

    @Autowired
    public HomeController(AuthService authService,
                          HomeService homeService, LoggedUser loggedUser, SongService songService) {
        this.authService = authService;
        this.homeService = homeService;
        this.loggedUser = loggedUser;
        this.songService = songService;
    }

    @GetMapping("/")
    public String loggedOutIndex(){
       if (this.authService.isLoggedIn()) {
           return "redirect:/home";
       }
       return "index";
   }

   @GetMapping("/home")
    public String loggedInIndex(Model model){
       if (!this.authService.isLoggedIn()) {
           return "redirect:/";
       }

       model.addAttribute ("songs", this.homeService.getSongs ());
       model.addAttribute ("sumOfDuration", this.songService.sumOfDuration (this.songService.getPlaylist (loggedUser.getId ())));
       model.addAttribute ("playlist", this.songService.getPlaylist(loggedUser.getId ()));



       return "home";
   }

    @GetMapping("/home/add-song-to-playlist/{id}")
    String addSongToPlaylist(@PathVariable("id") Long id){
        if (!this.authService.isLoggedIn()) {
            return "redirect:/";
        }
        this.homeService.addSong(id, this.loggedUser.getId ());
        return "redirect:/home";
    }

    @GetMapping("/home/remove-song-from-playlist/{id}")
    String removeSongFromPlaylist(@PathVariable("id") Long id){
        if (!this.authService.isLoggedIn()) {
            return "redirect:/";
        }
        this.homeService.removeSong(id, this.loggedUser.getId ());
        return "redirect:/home";
    }

    @GetMapping("/home/remove-all-song-from-playlist")
    String deleteAllFromPlaylist(){
        if (!this.authService.isLoggedIn()) {
            return "redirect:/";
        }
        this.homeService.deleteAll(this.loggedUser.getId());
        return "redirect:/home";
    }

    @ModelAttribute
    public SongsByGenreDTO songs() {
        return new SongsByGenreDTO();
    }

}

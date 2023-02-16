package com.example.exam.controllers;

import com.example.exam.service.AlbumService;
import com.example.exam.service.AuthService;
import com.example.exam.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {
    private final AuthService authService;
    private final HomeService homeService;
    private final AlbumService albumService;


    @Autowired
    public HomeController(AuthService authService,
                          HomeService homeService, AlbumService albumService) {
        this.authService = authService;
        this.homeService = homeService;
        this.albumService = albumService;
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
        model.addAttribute ("albums", this.homeService.getAllAlbums ());
        model.addAttribute ("totalCopies", this.homeService.getAlbumsTotalSoldCopies ());

        return "home";
    }

    @GetMapping("/delete/{id}")
    String deleteAlbum(@PathVariable("id") Long id){
        if (!this.authService.isLoggedIn()) {
            return "redirect:/";
        }
        this.albumService.deleteAlbumById (id);
        return "redirect:/home";
    }

}

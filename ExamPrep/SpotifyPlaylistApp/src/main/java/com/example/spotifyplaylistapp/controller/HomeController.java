package com.example.spotifyplaylistapp.controller;

import com.example.spotifyplaylistapp.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final AuthService authService;

    @Autowired
    public HomeController(AuthService authService) {
        this.authService = authService;
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

       return "home";
   }


}

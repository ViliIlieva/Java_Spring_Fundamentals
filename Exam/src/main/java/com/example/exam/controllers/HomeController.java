package com.example.exam.controllers;

import com.example.exam.service.AuthService;
import com.example.exam.service.HomeService;
import com.example.exam.service.OfferService;
import com.example.exam.session.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {
    private final AuthService authService;
    private final HomeService homeService;
    private final OfferService offerService;
    private final LoggedUser loggedUser;


    @Autowired
    public HomeController(AuthService authService,
                          HomeService homeService, OfferService offerService,
                          LoggedUser loggedUser) {
        this.authService = authService;
        this.homeService = homeService;
        this.offerService = offerService;
        this.loggedUser = loggedUser;
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
        model.addAttribute ("myOffers", this.homeService.getMyOffer ());
        model.addAttribute ("myBoughtOffers", this.homeService.boughtOffer ());
        model.addAttribute ("otherOffers", this.homeService.getOffersToOtherUsers ());
        return "home";
    }

    @GetMapping("/home/remove-offer/{id}")
    String removePost(@PathVariable("id") Long id){
        if (!this.authService.isLoggedIn()) {
            return "redirect:/";
        }
        this.offerService.removeOfferById (id);
        return "redirect:/home";
    }

    @GetMapping("/home/buy-offer/{id}")
    String buyOffer (@PathVariable("id") Long id){
        if (!this.authService.isLoggedIn()) {
            return "redirect:/";
        }

        this.homeService.buyOfferWithId (id, this.loggedUser.getId ());
        return "redirect:/home";
    }
}

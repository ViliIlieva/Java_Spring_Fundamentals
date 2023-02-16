package com.example.exam.controllers;

import com.example.exam.service.AuthService;
import com.example.exam.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final AuthService authService;
    private final HomeService homeService;


    @Autowired
    public HomeController(AuthService authService,
                          HomeService homeService) {
        this.authService = authService;
        this.homeService = homeService;
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
//        model.addAttribute ("orders", this.homeService.getAllOrders ());
//        model.addAttribute ("totalTime", this.homeService.getOrdersTotalTime ());
//        model.addAttribute ("ordersByEmployee", this.homeService.findCountOfOrdersByEmployee());
        return "home";
    }

}

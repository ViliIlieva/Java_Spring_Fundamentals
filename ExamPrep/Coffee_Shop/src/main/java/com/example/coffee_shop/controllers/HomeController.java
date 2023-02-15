package com.example.coffee_shop.controllers;

import com.example.coffee_shop.service.AuthService;
import com.example.coffee_shop.service.HomeService;
import com.example.coffee_shop.service.OrderService;
import com.example.coffee_shop.session.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {
    private final AuthService authService;
    private final HomeService homeService;
    private final OrderService orderService;

    @Autowired
    public HomeController(AuthService authService, HomeService homeService, OrderService orderService) {
        this.authService = authService;
        this.homeService = homeService;
        this.orderService = orderService;
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
        model.addAttribute ("orders", this.homeService.getAllOrders ());
        model.addAttribute ("totalTime", this.homeService.getOrdersTotalTime ());
        model.addAttribute ("ordersByEmployee", this.homeService.findCountOfOrdersByEmployee());
        return "home";
    }

    @GetMapping("/ready/{id}")
    String buyProduct(@PathVariable("id") Long id){
        if (!this.authService.isLoggedIn()) {
            return "redirect:/";
        }
        this.orderService.removeOrderById(id);
        return "redirect:/home";
    }
}

package com.example.shoppinglist.controler;

import com.example.shoppinglist.service.AuthService;
import com.example.shoppinglist.service.HomeService;
import com.example.shoppinglist.service.ProductService;
import com.example.shoppinglist.session.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final AuthService authService;
    private final HomeService homeService;
    private final LoggedUser loggedUser;
    private final ProductService productService;

    @Autowired
    public HomeController(AuthService authService,
                          HomeService homeService,
                          LoggedUser loggedUser,
                          ProductService productService) {
        this.authService = authService;
        this.homeService = homeService;
        this.loggedUser = loggedUser;
        this.productService = productService;
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

//       model.addAttribute ("products", this.homeService.getSongs ());
//       model.addAttribute ("sumOfDuration", this.songService.sumOfDuration (this.songService.getPlaylist (loggedUser.getId ())));
//       model.addAttribute ("playlist", this.songService.getPlaylist(loggedUser.getId ()));

       return "home";
   }

}

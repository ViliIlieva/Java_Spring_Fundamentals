package com.example.likebookapplication.controller;

import com.example.likebookapplication.model.dtos.UserRegistrationDTO;
import com.example.likebookapplication.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @ModelAttribute("registrationDTO")//ако го няма инициран да го създадем ДТО-то
    public UserRegistrationDTO initRegistrationDTO() {
        return new UserRegistrationDTO ();
    }

    @GetMapping("/register")
    public String register(){
        if (this.authService.isLoggedIn ()) {
            return "redirect:/home";
        }

        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid UserRegistrationDTO registrationDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes){

        if (this.authService.isLoggedIn ()) {
            return "redirect:/home";
        }

        if (bindingResult.hasErrors () || !this.authService.register (registrationDTO)) {
            redirectAttributes.addFlashAttribute ("registrationDTO", registrationDTO);
            redirectAttributes.addFlashAttribute (
                    "org.springframework.validation.BindingResult.registrationDTO", bindingResult);

            return "redirect:/register";
        }
        return "redirect:/login";
    }
}

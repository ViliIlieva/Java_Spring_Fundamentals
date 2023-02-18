package com.example.exam.controllers;

import com.example.exam.models.dtos.bindingModels.LoginDTO;
import com.example.exam.models.dtos.bindingModels.UserRegistrationDTO;
import com.example.exam.service.AuthService;
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

    @ModelAttribute("registrationDTO")
    public UserRegistrationDTO initRegistrationDTO() {
        return new UserRegistrationDTO ();
    }

    @ModelAttribute("loginDTO")
    public LoginDTO initLoginDTO() {
        return new LoginDTO ();
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

    @GetMapping("/login")
    public String login() {
        if (this.authService.isLoggedIn ()) {
            return "redirect:/home";
        }
        return "login";
    }

    @PostMapping("/login")
    public String login(@Valid LoginDTO loginDTO,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes) {

        if (this.authService.isLoggedIn ()) {
            return "redirect:/home";
        }

        if (bindingResult.hasErrors () || !this.authService.login (loginDTO)) {
            redirectAttributes.addFlashAttribute ("loginDTO", loginDTO);
            redirectAttributes.addFlashAttribute (
                    "org.springframework.validation.BindingResult.loginDTO", bindingResult);

            return "redirect:/login";
        }

        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout() {

        if(!this.authService.isLoggedIn ()){
            return "redirect:/";
        }

        this.authService.logout ();

        return "redirect:/";
    }
}

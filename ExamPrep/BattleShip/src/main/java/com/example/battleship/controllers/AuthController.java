package com.example.battleship.controllers;

import com.example.battleship.domain.dtos.LoginDTO;
import com.example.battleship.domain.dtos.UserRegistrationDTO;
import com.example.battleship.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

//чрез контролерите си правим връзките към html-ите
@Controller
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @ModelAttribute("registrationDTO")//ако го няма инициран да го създадем ДТО-то
    public UserRegistrationDTO initRegistrationDTO() {
        return new UserRegistrationDTO ();
    }

    @ModelAttribute("loginDTO")
    public LoginDTO initLoginDTO() {
        return new LoginDTO ();
    }

    @GetMapping("/register")//да връща регистрационната форма при поискване
    public String register() {
        if (this.authService.isLoggedIn ()) {
            return "redirect:/home";
        }

        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid UserRegistrationDTO registrationDTO,//трябва ни да проверим дто-то да е валидно
                           BindingResult bindingResult,//резултата от валидацията/грешките
                           RedirectAttributes redirectAttributes) {//да запазим грешките и да ги покажем в следващата сесия

        if (this.authService.isLoggedIn ()) {
            return "redirect:/home";
        }

        //ако информацията е невалидна, изкарва грешките и връща вярната информация попълнена в регистрационната форма
        // в authService сме направили всички проверки
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
        //ако сме логнати
        if (this.authService.isLoggedIn ()) {
            return "redirect:/home";
        }

        return "login";
    }

    @PostMapping("/login")
    public String login(@Valid LoginDTO loginDTO,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes) {
        //дали
        if (this.authService.isLoggedIn ()) {
            return "redirect:/home";
        }

        //ако има грешки от подадените във формата спрямо валидациите
        //изкарва грешките и връща под полетата валидация информация
        if (bindingResult.hasErrors ()) {
            redirectAttributes.addFlashAttribute ("loginDTO", loginDTO);
            redirectAttributes.addFlashAttribute (
                    "org.springframework.validation.BindingResult.loginDTO", bindingResult);

            return "redirect:/login";
        }

        //ако не сме го логнали успешно, няма го в базата, връщаме грешките и вярната информация
        if (!this.authService.login (loginDTO)) {
            redirectAttributes.addFlashAttribute ("loginDTO", loginDTO);
            redirectAttributes.addFlashAttribute ("badCredentials", true);

            return "redirect:/login";
        }

        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout() {
        this.authService.logout ();

        return "redirect:/";
    }

}

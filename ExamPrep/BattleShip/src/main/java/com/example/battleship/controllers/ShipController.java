package com.example.battleship.controllers;

import com.example.battleship.domain.dtos.CreateShipDTO;
import com.example.battleship.services.AuthService;
import com.example.battleship.services.ShipService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ShipController {

    private final ShipService shipService;

    private final AuthService authService;

    public ShipController(ShipService shipService, AuthService authService) {
        this.shipService = shipService;
        this.authService = authService;
    }

    @ModelAttribute("createShipDTO")//да го иницираме
    public CreateShipDTO initCreateShipDTO() {
        return new CreateShipDTO();
    }




    @GetMapping("/ships/add")
    public String ships() {
        //не логнат/гост/ няма достъп до тази страница задача.5
        if (!this.authService.isLoggedIn()) {
            return "redirect:/";
        }

        return "ship-add";
    }

    @PostMapping("/ships/add")
    public String ships(@Valid CreateShipDTO createShipDTO,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes) {

        //проверява дали юзъра е логнат
        if (!this.authService.isLoggedIn()) {
            return "redirect:/";
        }

        //ако има грешки при създаване на кораба
        if (bindingResult.hasErrors() || !this.shipService.create(createShipDTO)) {
            redirectAttributes.addFlashAttribute("createShipDTO", createShipDTO);
            redirectAttributes.addFlashAttribute(
            "org.springframework.validation.BindingResult.createShipDTO", bindingResult);

            return "redirect:/ships/add";
        }
        //ако сме добавили успешно кораб
        return "redirect:/home";
    }
}

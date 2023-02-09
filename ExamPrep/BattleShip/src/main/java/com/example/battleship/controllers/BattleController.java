package com.example.battleship.controllers;

import com.example.battleship.domain.dtos.StartBattleDTO;
import com.example.battleship.services.AuthService;
import com.example.battleship.services.BattleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class BattleController {

    private final BattleService battleService;
    private final AuthService authService;

    @Autowired
    public BattleController(BattleService battleService, AuthService authService) {
        this.battleService = battleService;
        this.authService = authService;
    }

    @PostMapping("/battle")
    public String battle(@Valid StartBattleDTO startBattleDTO,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {

        //ако не е логнат юзър няма достъп до тази страница задача.5
        if (!this.authService.isLoggedIn()) {
            return "redirect:/";
        }
        //ако има грешки да ги покажем и да презаредим
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("startBattleDTO", startBattleDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.startBattleDTO", bindingResult);

            return "redirect:/home";
        }
        //ако няма грешки да акатува и след това пак да презареди страницата
        this.battleService.attack(startBattleDTO);

        return "redirect:/home";
    }
}

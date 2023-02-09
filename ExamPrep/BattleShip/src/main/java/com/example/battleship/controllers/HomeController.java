package com.example.battleship.controllers;

import com.example.battleship.domain.dtos.ShipDTO;
import com.example.battleship.domain.dtos.StartBattleDTO;
import com.example.battleship.services.AuthService;
import com.example.battleship.services.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class HomeController {

    private final ShipService shipService;

    private final AuthService authService;

    @ModelAttribute("startBattleDTO")
    public StartBattleDTO initBattleForm() {
        return new StartBattleDTO();
    }

    @Autowired
    public HomeController(ShipService shipService, AuthService authService) {
        this.shipService = shipService;
        this.authService = authService;
    }

    //ако юзъра е логнат да се зарежда home
    @GetMapping("/")
    public String loggedOutIndex() {
        if (this.authService.isLoggedIn()) {
            return "redirect:/home";
        }
        //в противен случей госта има достъп само до началнат страница
        return "index";
    }


    @GetMapping("/home")
    public String loggedInIndex(Model model) {
        if (!this.authService.isLoggedIn()) {
            return "redirect:/";
        }
        //намираме ID-то на логнатия потребител, за да може да търсим по него
        long loggedUserId = this.authService.getLoggedUserId();
        //собствените кораби, метода е в сървиса и репозиторито
        List<ShipDTO> ownShips = this.shipService.getShipsOwnedBy(loggedUserId);
        //кораби на врага
        List<ShipDTO> enemyShips = this.shipService.getShipsNotOwnedBy(loggedUserId);
        List<ShipDTO> sortedShips = this.shipService.getAllSorted();

        model.addAttribute("ownShips", ownShips);
        model.addAttribute("enemyShips", enemyShips);
        model.addAttribute("sortedShips", sortedShips);

        return "home";
    }
}

package com.example.exam.controllers;

import com.example.exam.models.dtos.bindingModels.AddOfferDTO;
import com.example.exam.service.AuthService;
import com.example.exam.service.OfferService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class OfferController {
    private final AuthService authService;
    private final OfferService offerService;

    @Autowired
    public OfferController(AuthService authService, OfferService offerService) {
        this.authService = authService;
        this.offerService = offerService;
    }

    @ModelAttribute("addOfferDTO")
    public AddOfferDTO initAddOfferDTO() {
        return new AddOfferDTO ();
    }

    @GetMapping("/offers/add")
    public String offers() {
        if (!this.authService.isLoggedIn()) {
            return "redirect:/";
        }
        return "offer-add";
    }

    @PostMapping("/offers/add")
    public String offers(@Valid AddOfferDTO addOfferDTO,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        if (!this.authService.isLoggedIn()) {
            return "redirect:/";
        }
        if (bindingResult.hasErrors() || !this.offerService.addOffer (addOfferDTO)) {
            redirectAttributes.addFlashAttribute("addOfferDTO", addOfferDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.addOfferDTO", bindingResult);

            return "redirect:/offers/add";
        }
        return "redirect:/home";
    }



}

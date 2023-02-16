package com.example.exam.controllers;

import com.example.exam.models.dtos.bindingModels.AddAlbumDTO;
import com.example.exam.models.dtos.viewModels.AllAlbumDTO;
import com.example.exam.service.AlbumService;
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
public class AlbumController {
    private final AuthService authService;
    private final AlbumService albumService;

    @Autowired
    public AlbumController(AuthService authService, AlbumService albumService) {
        this.authService = authService;
        this.albumService = albumService;
    }

    @ModelAttribute("addAlbumDTO")
    public AddAlbumDTO initAddAlbumDTO() {
        return new AddAlbumDTO();
    }

    @ModelAttribute("AllAlbumDTO")
    public AllAlbumDTO initAllAlbumDTO() {
        return new AllAlbumDTO();
    }

    @GetMapping("/albums/add")
    public String albums() {
        if (!this.authService.isLoggedIn()) {
            return "redirect:/";
        }
        return "add-album";
    }

    @PostMapping("/albums/add")
    public String albums(@Valid AddAlbumDTO addAlbumDTO,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        if (!this.authService.isLoggedIn()) {
            return "redirect:/";
        }
        if (bindingResult.hasErrors() || !this.albumService.addAlbum(addAlbumDTO)) {
            redirectAttributes.addFlashAttribute("addAlbumDTO", addAlbumDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.addAlbumDTO", bindingResult);

            return "redirect:/albums/add";
        }
        return "redirect:/home";
    }

}

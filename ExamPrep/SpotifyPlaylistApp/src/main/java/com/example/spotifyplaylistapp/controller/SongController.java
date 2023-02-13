package com.example.spotifyplaylistapp.controller;

import com.example.spotifyplaylistapp.model.dtos.AddSongDTO;
import com.example.spotifyplaylistapp.service.AuthService;
import com.example.spotifyplaylistapp.service.SongService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SongController {

    private final AuthService authService;
    private final SongService songService;


    public SongController(AuthService authService, SongService songService) {
        this.authService = authService;
        this.songService = songService;
    }

    @ModelAttribute("addSongDTO")
    public AddSongDTO initAddSongDTO() {
        return new AddSongDTO();
    }

    @GetMapping("/songs/add")
    public String songs() {
        if (!this.authService.isLoggedIn()) {
            return "redirect:/";
        }

        return "song-add";
    }

    @PostMapping("/songs/add")
    public String songs(@Valid AddSongDTO addSongDTO,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes) {
        if (!this.authService.isLoggedIn()) {
            return "redirect:/";
        }

        if (bindingResult.hasErrors() || !this.songService.addSong(addSongDTO)) {
            redirectAttributes.addFlashAttribute("addSongDTO", addSongDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.addSongDTO", bindingResult);

            return "redirect:/songs/add";
        }
        return "redirect:/home";
    }
}

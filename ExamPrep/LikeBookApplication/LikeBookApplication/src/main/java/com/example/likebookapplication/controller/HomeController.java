package com.example.likebookapplication.controller;

import com.example.likebookapplication.model.dtos.PostDTO;
import com.example.likebookapplication.service.AuthService;
import com.example.likebookapplication.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    private final PostService postService;
    private final AuthService authService;

    @Autowired
    public HomeController(PostService postService, AuthService authService) {
        this.postService = postService;
        this.authService = authService;
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

        long loggedUserId = this.authService.getLoggedUserId();

        List<PostDTO> ownPost = this.postService.getPostOwnedBy (loggedUserId);
        model.addAttribute ("userPosts", ownPost);
        List<PostDTO> otherPost = this.postService.getPostNotOwnedBy(loggedUserId);
        model.addAttribute ("otherUserPosts", otherPost);
        return "home";
    }
}

package com.example.likebookapplication.controller;

import com.example.likebookapplication.model.dtos.AddPostDTO;
import com.example.likebookapplication.service.AuthService;
import com.example.likebookapplication.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PostController {
    private final PostService postService;
    private final AuthService authService;

    @Autowired
    public PostController(PostService postService, AuthService authService) {
        this.postService = postService;
        this.authService = authService;
    }

    @ModelAttribute("addPostDTO")
    public AddPostDTO initAddPostDTO(){
        return new AddPostDTO ();
    }



    @GetMapping("/post/add")
    public String posts(){
        if(!this.authService.isLoggedIn ()){
            return "redirect:/";
        }

        return "post-add";
    }

    @PostMapping("/post/add")
    public String posts(@Valid AddPostDTO addPostDTO,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes){
        if(!this.authService.isLoggedIn ()){
            return "redirect:/";
        }

        if (bindingResult.hasErrors() || !this.postService.addPost (addPostDTO)) {
            redirectAttributes.addFlashAttribute("addPostDTO", addPostDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.addPostDTO", bindingResult);

            return "redirect:/post/add";
        }

        return "redirect:/home";
    }




















}

package com.likebookapp.controller;

import com.likebookapp.model.dto.AddPostDTO;
import com.likebookapp.service.PostService;
import com.likebookapp.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class PostController {
    private final PostService postService;
    private final UserService userService;

    public PostController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @ModelAttribute("postData")
    public AddPostDTO postData() {
        return new AddPostDTO();
    }

    @GetMapping("/post-add")
    public String viewAddPost() {
        return userService.isUserLoggedIn() ? "post-add" : "redirect:/login";
    }

    @PostMapping("/post-add")
    public String addPost(@Valid AddPostDTO postData,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {
        if (!userService.isUserLoggedIn()) {
            return "redirect:/login";
        }
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("postData", postData);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.postData", bindingResult);
            return "redirect:/post-add";
        }

        postService.addPost(postData, userService.getLoggedUserId());
        return "redirect:/home";
    }
}

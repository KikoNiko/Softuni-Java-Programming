package com.likebookapp.controller;

import com.likebookapp.model.dto.PostInfoDTO;
import com.likebookapp.service.PostService;
import com.likebookapp.util.UserSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.util.List;

@Controller
public class HomeController {
    private final UserSession userSession;
    private final PostService postService;

    public HomeController(UserSession userSession, PostService postService) {
        this.userSession = userSession;
        this.postService = postService;
    }

    @GetMapping("/")
    public String viewNotLogged() {
        return userSession.isLoggedIn() ? "redirect:/home" : "index";
    }

    @GetMapping("/home")
    @Transactional
    public String viewLoggedHome(Model model) {
        if (userSession.isLoggedIn()) {

            List<PostInfoDTO> currentUserPosts = postService.getAllPostsByUser(userSession.getId());
            model.addAttribute("currentUserPosts", currentUserPosts);

            List<PostInfoDTO> otherUsersPosts = postService.getAllOtherPosts(userSession.getId());
            model.addAttribute("otherUsersPosts", otherUsersPosts);

            return "home";
        }

        return "redirect:/";
    }

    @GetMapping("/home/like-post/{id}")
    @Transactional
    public String likePost(@PathVariable long id) {
        if (!userSession.isLoggedIn()) {
            return "redirect:/login";
        }
        postService.likePost(id, userSession.getId());
        return "redirect:/home";
    }

    @GetMapping("/home/remove-post/{id}")
    @Transactional
    public String removePost(@PathVariable long id) {
        if (!userSession.isLoggedIn()) {
            return "redirect:/login";
        }
        postService.removePost(id);
        return "redirect:/home";
    }
}

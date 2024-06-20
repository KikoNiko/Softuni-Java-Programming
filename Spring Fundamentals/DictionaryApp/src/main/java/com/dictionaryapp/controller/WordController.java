package com.dictionaryapp.controller;

import com.dictionaryapp.model.dto.WordAddDTO;
import com.dictionaryapp.service.WordService;
import com.dictionaryapp.util.UserSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class WordController {
    private final WordService wordService;
    private final UserSession userSession;

    public WordController(WordService wordService, UserSession userSession) {
        this.wordService = wordService;
        this.userSession = userSession;
    }

    @ModelAttribute("wordData")
    public WordAddDTO wordData() {
        return new WordAddDTO();
    }

    @GetMapping("/word-add")
    public String addWord() {
        return userSession.isLoggedIn() ? "word-add" : "redirect:/login";
    }

    @PostMapping("/word-add")
    public String addWord(@Valid WordAddDTO wordAddDTO,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("wordData", wordAddDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.wordData", bindingResult);
            return "redirect:/word-add";
        }
        wordService.add(wordAddDTO, userSession.getId());

        return "redirect:/home";
    }
}

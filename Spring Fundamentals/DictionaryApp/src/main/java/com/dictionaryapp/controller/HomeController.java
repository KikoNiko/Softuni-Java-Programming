package com.dictionaryapp.controller;

import com.dictionaryapp.model.entity.LanguageEnum;
import com.dictionaryapp.model.entity.Word;
import com.dictionaryapp.service.WordService;
import com.dictionaryapp.util.UserSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class HomeController {
    private final UserSession userSession;
    private final WordService wordService;

    public HomeController(UserSession userSession, WordService wordService) {
        this.userSession = userSession;
        this.wordService = wordService;
    }

    @GetMapping("/")
    public String notLogged() {
        if (userSession.isLoggedIn()) {
            return "redirect:/home";
        }
        return "index";
    }

    @GetMapping("/home")
    public String logged(Model model) {
        if (!userSession.isLoggedIn()) {
            return "redirect:/";
        }

        List<Word> germanWords = wordService.findAllByLanguage(LanguageEnum.GERMAN);
        List<Word> frenchWords = wordService.findAllByLanguage(LanguageEnum.FRENCH);
        List<Word> spanishWords = wordService.findAllByLanguage(LanguageEnum.SPANISH);
        List<Word> italianWords = wordService.findAllByLanguage(LanguageEnum.ITALIAN);
        model.addAttribute("germanWords", germanWords);
        model.addAttribute("frenchWords", frenchWords);
        model.addAttribute("spanishWords", spanishWords);
        model.addAttribute("italianWords", italianWords);

        long wordsCount = wordService.countAllWords();
        model.addAttribute("wordsCount", wordsCount);

        return "home";
    }

    @GetMapping("/remove-word-by-id/{id}")
    public String removeById(@PathVariable("id") Long id) {
        wordService.removeById(id);
        return "redirect:/home";
    }
}

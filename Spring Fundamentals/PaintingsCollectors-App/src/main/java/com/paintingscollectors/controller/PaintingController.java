package com.paintingscollectors.controller;

import com.paintingscollectors.model.dto.AddPaintingDTO;
import com.paintingscollectors.model.entity.StyleName;
import com.paintingscollectors.service.PaintingService;
import com.paintingscollectors.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.transaction.Transactional;
import javax.validation.Valid;

@Controller
public class PaintingController {

    private final PaintingService paintingService;
    private final UserService userService;

    public PaintingController(PaintingService paintingService, UserService userService) {
        this.paintingService = paintingService;

        this.userService = userService;
    }

    @ModelAttribute("paintingData")
    public AddPaintingDTO paintingData() {
        return new AddPaintingDTO();
    }

    @ModelAttribute("styleNames")
    public StyleName[] styleNames() {
        return StyleName.values();
    }

    @GetMapping("/add-painting")
    public String viewAddPainting() {
        return userService.isLoggedUser() ? "add-painting" : "redirect:/login";
    }

    @PostMapping("/add-painting")
    @Transactional
    public String addPainting(@Valid AddPaintingDTO paintingData,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {
        if (!userService.isLoggedUser()) {
            return "redirect:/login";
        }
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("paintingData", paintingData);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.paintingData", bindingResult);
            return "redirect:/add-painting";
        }
        paintingService.addPainting(paintingData, userService.getLoggedUserId());
        return "redirect:/home";
    }
}

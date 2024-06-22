package com.plannerapp.controller;

import com.plannerapp.model.dto.TaskInfoDTO;
import com.plannerapp.model.entity.Task;
import com.plannerapp.service.TaskService;
import com.plannerapp.util.UserSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.util.List;

@Controller
public class HomeController {
    private final UserSession userSession;
    private final TaskService taskService;

    public HomeController(UserSession userSession, TaskService taskService) {
        this.userSession = userSession;
        this.taskService = taskService;
    }

    @GetMapping("/")
    public String viewNotLogged() {
        return userSession.isLoggedIn() ? "redirect:/home" : "index";
    }

    @GetMapping("/home")
    @Transactional
    public String viewLogged(Model model) {
        if (!userSession.isLoggedIn()) {
            return "redirect:/";
        }
        List<TaskInfoDTO> unassignedTasks = taskService.getAllTasks(null);
        List<TaskInfoDTO> assignedTasks = taskService.getAllTasks(userSession.getId());
        model.addAttribute("assignedTasks", assignedTasks);
        model.addAttribute("unassignedTasks", unassignedTasks);

        return "home";
    }

    @GetMapping("/home/assign-task/{id}")
    @Transactional
    public String assignTask(@PathVariable Long id) {
        if (!userSession.isLoggedIn()) {
            return "redirect:/";
        }
        taskService.assignTaskToUser(id, userSession.getId());
        return "redirect:/home";
    }

    @GetMapping("/home/return-task/{id}")
    @Transactional
    public String returnTask(@PathVariable Long id) {
        if (!userSession.isLoggedIn()) {
            return "redirect:/";
        }
        taskService.returnTask(id, userSession.getId());
        return "redirect:/home";
    }

    @GetMapping("/home/remove-task/{id}")
    @Transactional
    public String removeTask(@PathVariable Long id) {
        if (!userSession.isLoggedIn()) {
            return "redirect:/";
        }
        taskService.removeTask(id, userSession.getId());
        return "redirect:/home";
    }

}

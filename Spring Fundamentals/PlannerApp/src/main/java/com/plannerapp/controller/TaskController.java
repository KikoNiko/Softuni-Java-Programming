package com.plannerapp.controller;

import com.plannerapp.model.dto.AddTaskDTO;
import com.plannerapp.service.TaskService;
import com.plannerapp.util.UserSession;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class TaskController {

    private final TaskService taskService;
    private final UserSession userSession;

    public TaskController(TaskService taskService, UserSession userSession) {
        this.taskService = taskService;
        this.userSession = userSession;
    }

    @ModelAttribute("taskData")
    public AddTaskDTO taskData() {
        return new AddTaskDTO();
    }

    @GetMapping("/task-add")
    public String viewAddTask() {
        return userSession.isLoggedIn() ? "task-add" : "redirect:/login";
    }

    @PostMapping("/task-add")
    public String addTask(@Valid AddTaskDTO taskData,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {
        if (!userSession.isLoggedIn()) {
            return "redirect:/login";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("taskData", taskData);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.taskData", bindingResult);

            return "redirect:/task-add";
        }

        taskService.addTask(taskData);
        return "redirect:/home";
    }
}

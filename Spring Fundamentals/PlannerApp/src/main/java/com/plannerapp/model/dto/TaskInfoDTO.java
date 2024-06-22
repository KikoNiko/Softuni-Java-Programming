package com.plannerapp.model.dto;

import com.plannerapp.model.entity.PriorityName;
import com.plannerapp.model.entity.Task;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class TaskInfoDTO {
    private Long id;

    private String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDate;

    private PriorityName priorityName;

    public TaskInfoDTO(Task task) {
        this.id = task.getId();
        this.description = task.getDescription();
        this.dueDate = task.getDueDate();
        this.priorityName = task.getPriority().getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public PriorityName getPriorityName() {
        return priorityName;
    }

    public void setPriorityName(PriorityName priorityName) {
        this.priorityName = priorityName;
    }
}

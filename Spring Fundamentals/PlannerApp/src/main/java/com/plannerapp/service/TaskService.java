package com.plannerapp.service;

import com.plannerapp.model.dto.AddTaskDTO;
import com.plannerapp.model.dto.TaskInfoDTO;
import com.plannerapp.model.entity.Task;

import java.util.List;

public interface TaskService {

    void addTask(AddTaskDTO taskData);

    List<TaskInfoDTO> getAllTasks(Long id);

    void assignTaskToUser(Long taskId, Long userId);

    void returnTask(Long taskId, Long userId);

    void removeTask(Long taskId, Long userId);
}

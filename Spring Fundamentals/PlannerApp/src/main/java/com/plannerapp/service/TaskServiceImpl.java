package com.plannerapp.service;

import com.plannerapp.model.dto.AddTaskDTO;
import com.plannerapp.model.dto.TaskInfoDTO;
import com.plannerapp.model.entity.Priority;
import com.plannerapp.model.entity.Task;
import com.plannerapp.model.entity.User;
import com.plannerapp.repo.PriorityRepository;
import com.plannerapp.repo.TaskRepository;
import com.plannerapp.repo.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService{

    private final TaskRepository taskRepository;
    private final PriorityRepository priorityRepository;
    private final UserRepository userRepository;

    public TaskServiceImpl(TaskRepository taskRepository, PriorityRepository priorityRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.priorityRepository = priorityRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void addTask(AddTaskDTO taskData) {

        Task task = new Task();
        task.setDescription(taskData.getDescription());
        Priority priority = priorityRepository.findByName(taskData.getPriority()).orElse(null);
        task.setPriority(priority);
        task.setDueDate(taskData.getDueDate());

        taskRepository.save(task);
    }

    @Override
    public List<TaskInfoDTO> getAllTasks(Long id) {
        return taskRepository.findAllByUserId(id)
                .stream()
                .map(TaskInfoDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public void assignTaskToUser(Long taskId, Long userId) {
        Task task = taskRepository.findById(taskId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);
        task.setUser(user);
        user.getAssignedTasks().add(task);
        taskRepository.save(task);
        userRepository.save(user);
    }

    @Override
    public void returnTask(Long taskId, Long userId) {
        Task task = taskRepository.findById(taskId).orElse(null);
        task.setUser(null);
        User user = userRepository.findById(userId).orElse(null);
        user.getAssignedTasks().remove(task);
        taskRepository.save(task);
        userRepository.save(user);
    }

    @Override
    public void removeTask(Long taskId, Long userId) {
        Optional<Task> taskById = taskRepository.findById(taskId);
        if (taskById.isEmpty()){
            return;
        }
        returnTask(taskId, userId);
        taskRepository.delete(taskById.get());
    }
}

package com.example.challenge.service.Impl;

import com.example.challenge.model.Task;
import com.example.challenge.model.dto.TaskDto;
import com.example.challenge.repository.TaskRepository;
import com.example.challenge.service.ITaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.challenge.model.dto.TaskDto.taskToDto;

@Service
@RequiredArgsConstructor
public class TaskService implements ITaskService {

    private final TaskRepository taskRepository;

    @Override
    public TaskDto createTask(String name) {
        Task task = taskRepository.save(new Task(name));
        return taskToDto(task);
    }

    @Override
    public TaskDto editTask(Long id, String name) {
        Task task = taskRepository.getById(id);
        task.setName(name);
        return taskToDto(task);
    }

    @Override
    public boolean deleteTask(Long id) {
        taskRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean checkTask(Long id) {
        Task task = taskRepository.getById(id);
        task.setFinished(!task.getFinished());
        return task.getFinished();
    }
}

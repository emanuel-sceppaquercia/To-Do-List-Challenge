package com.example.challenge.service.Impl;

import com.example.challenge.model.Folder;
import com.example.challenge.model.Task;
import com.example.challenge.model.dto.TaskDto;
import com.example.challenge.model.dto.TaskRequestDto;
import com.example.challenge.repository.FolderRepository;
import com.example.challenge.repository.TaskRepository;
import com.example.challenge.service.ITaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.challenge.model.dto.TaskDto.taskToDto;

@Service
@RequiredArgsConstructor
public class TaskService implements ITaskService {

    private final TaskRepository taskRepository;
    private final FolderRepository folderRepository;

    @Override
    public TaskDto createTask(TaskRequestDto request) {
        Folder folder = folderRepository.getById(request.getFolderId());
        Task task = taskRepository.save(new Task(request.getName(),folder));
        return taskToDto(task);
    }

    @Override
    public TaskDto editTask(Long id, String name) {
        Task task = taskRepository.getById(id);
        task.setName(name);
        taskRepository.save(task);
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
        taskRepository.save(task);
        return task.getFinished();
    }
}

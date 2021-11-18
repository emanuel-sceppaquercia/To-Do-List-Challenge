package com.example.challenge.service.Impl;

import com.example.challenge.exception.ConflictException;
import com.example.challenge.exception.NotFoundException;
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
        Folder folder = folderRepository.findById(request.getFolderId())
                .orElseThrow( () -> new NotFoundException("Folder doesn't exist"));

        if(folder.getTasks().stream().anyMatch( p -> p.getName().equals(request.getName())))
            throw new ConflictException("Name already exist");

        return taskToDto(taskRepository.save(new Task(request.getName(),folder)));
    }

    @Override
    public TaskDto editTask(Long id, String name) {
        if(taskRepository.existsByName(name))
            throw new ConflictException("Name already exist");

        Task task = taskRepository.findById(id)
                .orElseThrow( () -> new NotFoundException("Invalid ID"));
        task.setName(name);
        taskRepository.save(task);
        return taskToDto(task);
    }

    @Override
    public void deleteTask(Long id) {
        if (taskRepository.existsById(id))
            taskRepository.deleteById(id);
        else
            throw new NotFoundException("Invalid ID");
    }

    @Override
    public void checkTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow( () -> new NotFoundException("Invalid ID"));
        task.setFinished(!task.getFinished());
        taskRepository.save(task);
    }
}

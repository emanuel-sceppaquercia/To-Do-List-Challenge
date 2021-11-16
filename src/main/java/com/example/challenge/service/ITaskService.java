package com.example.challenge.service;

import com.example.challenge.model.dto.TaskDto;
import com.example.challenge.model.dto.TaskRequestDto;

public interface ITaskService {

    TaskDto createTask(TaskRequestDto request);
    TaskDto editTask(Long id, String name);
    boolean deleteTask(Long id);
    boolean checkTask(Long id);

}

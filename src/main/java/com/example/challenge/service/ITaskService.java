package com.example.challenge.service;

import com.example.challenge.model.dto.TaskDto;

public interface ITaskService {

    TaskDto createTask(String name);
    TaskDto editTask(Long id, String name);
    boolean deleteTask(Long id);
    boolean checkTask(Long id);

}

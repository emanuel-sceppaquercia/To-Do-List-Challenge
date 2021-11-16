package com.example.challenge.service;

import com.example.challenge.model.dto.FolderDto;
import com.example.challenge.model.dto.TaskDto;

import java.util.List;

public interface IFolderService {

    FolderDto CreateFolder(String name);
    Boolean deleteFolder(Long id);
    List<TaskDto> viewAllTasks(Long id);

}

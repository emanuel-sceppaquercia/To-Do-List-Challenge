package com.example.challenge.service.Impl;

import com.example.challenge.model.Folder;
import com.example.challenge.model.dto.FolderDto;
import com.example.challenge.model.dto.TaskDto;
import com.example.challenge.repository.FolderRepository;
import com.example.challenge.repository.TaskRepository;
import com.example.challenge.service.IFolderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.challenge.model.dto.FolderDto.folderToDto;

@Service
@RequiredArgsConstructor
public class FolderServiceImpl implements IFolderService {

    private final FolderRepository folderRepository;
    private final TaskRepository taskRepository;
    private final ModelMapper mapper;

    @Override
    public FolderDto CreateFolder(String name) {
        Folder folder = folderRepository.save(new Folder(name));
        return folderToDto(folder);
    }

    @Override
    public Boolean deleteFolder(Long id) {
        folderRepository.deleteById(id);
        return true;
    }

    @Override
    public List<TaskDto> viewAllTasks(Long id) {
        return taskRepository.findAll()
                .stream()
                .map(Task -> mapper.map(Task, TaskDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<FolderDto> getAllFolders() {
        return folderRepository.findAll()
                .stream()
                .map(FolderDto::folderToDto)
                .collect(Collectors.toList());
    }


}

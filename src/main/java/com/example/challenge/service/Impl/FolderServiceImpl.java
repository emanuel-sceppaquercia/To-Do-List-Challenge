package com.example.challenge.service.Impl;

import com.example.challenge.exception.ConflictException;
import com.example.challenge.exception.NotFoundException;
import com.example.challenge.model.Folder;
import com.example.challenge.model.dto.FolderDto;
import com.example.challenge.model.dto.TaskDto;
import com.example.challenge.repository.FolderRepository;
import com.example.challenge.service.IFolderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.challenge.model.dto.FolderDto.folderToDto;

@Service
@RequiredArgsConstructor
public class FolderServiceImpl implements IFolderService {

    private final FolderRepository folderRepository;

    @Override
    public FolderDto createFolder(String name) {
        if(folderRepository.existsByName(name))
            throw new ConflictException("Name already exist");

        Folder folder = folderRepository.save(new Folder(name));
        return folderToDto(folder);
    }

    @Override
    public void deleteFolder(Long id) {
        if (folderRepository.existsById(id))
            folderRepository.deleteById(id);
        else
            throw new NotFoundException("Invalid ID");
    }

    @Override
    public List<TaskDto> viewAllTasks(Long id) {
        if(folderRepository.existsById(id))
            return folderRepository.getById(id)
                    .getTasks()
                    .stream()
                    .map(TaskDto::taskToDto)
                    .collect(Collectors.toList());
        else
            throw new NotFoundException("Folder doesn't exist");
    }

}

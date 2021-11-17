package com.example.challenge.controller;

import com.example.challenge.model.dto.RequestDto;
import com.example.challenge.service.IFolderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/folder")
public class FolderController {

    private final IFolderService folderService;

    @PostMapping
    public ResponseEntity<?> createFolder(@Valid @RequestBody RequestDto request){

        return ResponseEntity.ok(folderService.createFolder(request.getName()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFolder(@PathVariable(name = "id") Long id){
        folderService.deleteFolder(id);

        return ResponseEntity.ok("Deleted");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> viewAllTasks(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(folderService.viewAllTasks(id));
    }

    @GetMapping
    public ResponseEntity<?> viewAllFolders(){
        return ResponseEntity.ok(folderService.getAllFolders());
    }


}

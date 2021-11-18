package com.example.challenge.controller;

import com.example.challenge.model.dto.RequestDto;
import com.example.challenge.model.dto.TaskRequestDto;
import com.example.challenge.service.ITaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/task")
public class TaskController {

    private final ITaskService taskService;

    @PostMapping
    public ResponseEntity<?> createTask(@Valid @RequestBody TaskRequestDto request){
        return ResponseEntity.ok(taskService.createTask(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editTask(@Valid @PathVariable(name = "id") Long id,
                                      @RequestBody RequestDto request){
        return ResponseEntity.ok(taskService.editTask(id, request.getName()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable(name = "id") Long id){
        taskService.deleteTask(id);
        return ResponseEntity.ok("Deleted");
    }

    @PutMapping("/check/{id}")
    public ResponseEntity<?> checkTask(@PathVariable(name = "id") Long id){
        taskService.checkTask(id);
        return ResponseEntity.ok("Done");
    }

}

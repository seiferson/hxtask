package com.seiferson.hxtask.controller;

import com.seiferson.hxtask.model.Task;
import com.seiferson.hxtask.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class APIController {

    @Autowired
    private TaskRepository taskRepo;

    @GetMapping("/api/v1/tasks")
    public ResponseEntity<List<Task>> getAllTasks(Authentication authentication) {
        return ResponseEntity.ok(taskRepo.findByOwner(authentication.getName()));
    }

    @PostMapping("/api/v1/tasks")
    public Task createTask(@RequestBody Task task, Authentication authentication) {
        task.setOwner(authentication.getName());
        task.setCreated(new Date());
        task.setUpdated(new Date());
        return taskRepo.insert(task);
    }
}

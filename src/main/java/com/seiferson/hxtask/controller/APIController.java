package com.seiferson.hxtask.controller;

import com.seiferson.hxtask.model.Task;
import com.seiferson.hxtask.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/api/v1/tasks/{id}")
    public ResponseEntity<Task> getTask(Authentication authentication, @PathVariable String id) {
        Optional<Task> task = taskRepo.findById(id);

        if (task.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(task.get());
    }

    @PostMapping("/api/v1/tasks")
    public Task createTask(@RequestBody Task task, Authentication authentication) {
        task.setOwner(authentication.getName());
        task.setCreated(new Date());
        task.setUpdated(new Date());
        task.setCompleted(false);
        return taskRepo.insert(task);
    }

    @PatchMapping("/api/v1/tasks/{id}")
    public ResponseEntity<Task> updateTask(Authentication authentication) {
        return ResponseEntity.ok(null);
    }

    @PutMapping("/api/v1/tasks/{id}")
    public ResponseEntity<Task> replaceTask(Authentication authentication) {
        return ResponseEntity.ok(null);
    }
}

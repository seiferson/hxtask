package com.seiferson.hxtask.controller;

import com.seiferson.hxtask.model.Task;
import com.seiferson.hxtask.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class APIController {

    @Autowired
    private TaskRepository taskRepo;

    @GetMapping("/api/v1/tasks")
    public List<Task> getAllTasks() {
        return taskRepo.findAll();
    }

    @PostMapping("/api/v1/tasks")
    public Task createTask(@RequestBody Task task) {
        task.setStatus("created");
        return taskRepo.insert(task);
    }
}

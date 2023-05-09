package com.seiferson.hxtask.repository;

import com.seiferson.hxtask.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends MongoRepository<Task, String> {
    List<Task> findByOwner(String owner);
}

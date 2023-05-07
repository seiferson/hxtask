package com.seiferson.hxtask.repository;

import com.seiferson.hxtask.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskRepository extends MongoRepository<Task, String> {

}

package org.example.lab5.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@Service
public class TaskService {
    TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository repository) {
        this.taskRepository = repository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }


    //Craete
    @PostMapping
    public Task createTask(@RequestBody Task task) {
            return taskRepository.save(task);
    }

    //Update
    @PostMapping
    public Task updateTask(@RequestBody Task task) {
        if (taskRepository.existsById(task.getId())) {
            return taskRepository.save(task);
        }
        else return null;
    }

    //Delete
    @DeleteMapping
    public void deleteTask(@RequestBody Task task) {
        if (taskRepository.existsById(task.getId())) {
            taskRepository.deleteById(task.getId());
        }
    }
}

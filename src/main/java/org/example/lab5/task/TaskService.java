package org.example.lab5.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    public Task createTask(Task task) {
            return taskRepository.save(task);
    }



    public Task updateTask(Task task) {
        if (taskRepository.existsById(task.getId())) {
            return taskRepository.save(task);
        }
        else return null;
    }

    public void deleteTaskById(long id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
        }
    }

    public Task findTaskById(long id) {
        return taskRepository.findById(id).orElse(null);
    }

    public List<Task> getAllTasksSortedByDueDateAsc() {
        return taskRepository.findAllByOrderByDueDateAsc();
    }
    public List<Task> getAllTasksSortedByDueDateDesc() {
        return taskRepository.findAllByOrderByDueDateDesc();
    }
}

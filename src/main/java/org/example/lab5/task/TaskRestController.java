package org.example.lab5.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks/api")
public class TaskRestController {
    private final TaskService taskService;

    @Autowired
    public TaskRestController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getAllTasks(@RequestParam(required = false, defaultValue = "false") boolean sortedByCategoryAsc, @RequestParam(required = false, defaultValue = "false") boolean sortedByCategoryDesc) {
        if (sortedByCategoryAsc) {
            return taskService.getAllTasksSortedByDueDateAsc();
        } else if (sortedByCategoryDesc) {
            return taskService.getAllTasksSortedByDueDateDesc();
        }
        return taskService.getAllTasks();
    }

    @PostMapping("/create")
    public Task createTask(Task task) {
        return taskService.createTask(task);
    }

    @PostMapping("/save")
    public Task saveTask(Task task) {
        return taskService.updateTask(task);
    }


}

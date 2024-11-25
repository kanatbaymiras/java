package org.example.lab5.pages;

import org.example.lab5.category.Category;
import org.example.lab5.category.CategoryService;
import org.example.lab5.task.Task;
import org.example.lab5.task.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PagesController {
    private final TaskService taskService;
    private final CategoryService categoryService;

    @Autowired
    public PagesController(TaskService taskService, CategoryService categoryService) {
        this.taskService = taskService;
        this.categoryService = categoryService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/home")
    public String home(Model model) {
        List<Task> tasks = taskService.getAllTasks();
        model.addAttribute("tasks", tasks);
        return "home";
    }

    @GetMapping("/create")
    public String create(Model model ) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "create";
    }
}

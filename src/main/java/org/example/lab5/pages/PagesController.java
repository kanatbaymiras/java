package org.example.lab5.pages;

import org.example.lab5.category.Category;
import org.example.lab5.category.CategoryService;
import org.example.lab5.task.Task;
import org.example.lab5.task.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String home(Model model, @RequestParam(required = false, defaultValue = "false") boolean sortedByDueDateAsc, @RequestParam(required = false, defaultValue = "false") boolean sortedByDueDateDesc) {
        List<Task> tasks = taskService.getAllTasks();
        if (sortedByDueDateAsc) {
            tasks = taskService.getAllTasksSortedByDueDateAsc();
        } else if (sortedByDueDateDesc) {
            tasks = taskService.getAllTasksSortedByDueDateDesc();
        }
        model.addAttribute("tasks", tasks);
        return "home";
    }

    @GetMapping("/")
    public String redirectToHome() {
        return "redirect:/home";
    }

    @GetMapping("/create")
    public String create(Model model ) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "create";
    }
}

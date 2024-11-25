package org.example.lab5.task;

import org.example.lab5.category.Category;
import org.example.lab5.category.CategoryService;
import org.example.lab5.user.User;
import org.example.lab5.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;
    private final UserService userService;
    private final CategoryService categoryService;

    @Autowired
    public TaskController(TaskService taskService, UserService userService, CategoryService categoryService) {
        this.taskService = taskService;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @PostMapping
    public Task createTask(Task task) {
        return taskService.createTask(task);
    }

    @PostMapping("/create")
    public String createTask(@RequestParam String title, @RequestParam String description, @RequestParam String priority, @RequestParam String due_date, @RequestParam(name = "selectedCategories") List<Long> selectedCategoriesIDs) {
        Task task = new Task();

        task.setTitle(title);
        task.setDescription(description);
        task.setPriority(priority);

        List<Category> categories = categoryService.findCategoriesByIds(selectedCategoriesIDs);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        User user = userService.findByUsername(currentUsername);

        task.setUser(user);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime localDateTime = LocalDateTime.parse(due_date, formatter);

        task.setDue_date(localDateTime);

        task.setCategories(categories);
        taskService.createTask(task);

        return "redirect:/home";
    }

    @PostMapping("/update")
    public String updateTask(@RequestParam Long id, @RequestParam String title, @RequestParam String description, @RequestParam String priority, @RequestParam String due_date, @RequestParam(name = "selectedCategories") List<Long> selectedCategoriesIDs) {
        Task task = new Task();

        task.setId(id);

        task.setTitle(title);
        task.setDescription(description);
        task.setPriority(priority);

        List<Category> categories = categoryService.findCategoriesByIds(selectedCategoriesIDs);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        User user = userService.findByUsername(currentUsername);

        task.setUser(user);

        task.setCategories(categories);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime localDateTime = LocalDateTime.parse(due_date, formatter);

        task.setDue_date(localDateTime);

        taskService.updateTask(task);



        return "redirect:/home";
    }

    @GetMapping("/editById")
    public String editById(Model model, @RequestParam Long id) {
        Task task = taskService.findTaskById(id);
        model.addAttribute("task", task);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        String formattedDateTime = task.getDue_date().format(formatter);  // Correct format

        model.addAttribute("formattedDateTime", formattedDateTime);
        return "edit";
    }


    @GetMapping("/delete")
    public String deleteTask(@RequestParam Long id) {
        taskService.deleteTaskById(id);
        return "redirect:/home";
    }


}

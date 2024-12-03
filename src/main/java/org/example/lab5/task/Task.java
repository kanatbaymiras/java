package org.example.lab5.task;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import org.example.lab5.category.Category;
import org.example.lab5.user.User;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "Tasks")
@Entity
public class Task {
    @SequenceGenerator(name = "tasks_seq_gen", sequenceName = "tasks_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tasks_seq_gen")
    @Id
    private long id;

    @Valid
    @NotNull(message = "Task title cannot be null")
    private String title;
    private String description;
    private String status;
    private String priority;

    @FutureOrPresent(message = "due_date should not be in the past.")
    private LocalDateTime dueDate;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public @FutureOrPresent(message = "due_date should not be in the past.") LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(@FutureOrPresent(message = "due_date should not be in the past.") LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public @Valid @NotNull(message = "Task title cannot be null") String getTitle() {
        return title;
    }

    public void setTitle(@Valid @NotNull(message = "Task title cannot be null") String title) {
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @JsonManagedReference
    @ManyToMany
    @JoinTable(
            name = "task_categories",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;

    @PrePersist
    private void onCreate() {
        status = "in progress";
    }

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private User user;


}

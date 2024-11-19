package org.example.lab5.task;

import jakarta.persistence.*;
import org.example.lab5.category.Category;
import org.example.lab5.user.User;

import java.time.LocalDateTime;

@Table(name = "Tasks")
@Entity
public class Task {
    @SequenceGenerator(name = "tasks_seq_gen", sequenceName = "tasks_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tasks_seq_gen")
    @Id
    private long id;

    private String title;
    private String description;
    private String status;
    private String priority;
    private LocalDateTime due_date;

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public LocalDateTime getDue_date() {
        return due_date;
    }

    public void setDue_date(LocalDateTime due_date) {
        this.due_date = due_date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}

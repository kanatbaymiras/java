package org.example.lab5.category;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.example.lab5.task.Task;

import java.util.List;


@Table(name="categories")
@Entity
public class Category {
    @SequenceGenerator(name = "categories_seq_gen", sequenceName = "categories_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categories_seq_gen")
    @Id
    private long id;

    private String title;

    @JsonBackReference
    @ManyToMany(mappedBy = "categories")
    private List<Task> tasks;

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

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}


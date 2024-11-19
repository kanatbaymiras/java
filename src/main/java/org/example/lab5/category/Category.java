package org.example.lab5.category;

import jakarta.persistence.*;
import org.example.lab5.task.Task;

import java.util.List;


@Table(name="categories")
@Entity
public class Category {
    @SequenceGenerator(name = "categories_seq_gen", sequenceName = "categories_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categories_seq_gen")
    @Id
    private long id;

    private String title;

    @OneToMany(mappedBy = "category")
    private List<Task> tasks;
}


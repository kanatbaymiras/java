package org.example.lab5.task;

import jakarta.persistence.*;
import org.example.lab5.category.Category;
import org.example.lab5.user.User;

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
    private String due_date;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    private Category category;
}

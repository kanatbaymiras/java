package org.example.lab5.user;

import jakarta.persistence.*;
import org.example.lab5.task.Task;


import java.time.LocalDateTime;
import java.util.List;

@Table(name="users")
@Entity
public class User {
    @SequenceGenerator(name = "users_seq_gen", sequenceName = "users_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq_gen")
    @Id
    private long id;

    @Column(unique=true)
    private String username;

    private String password;

    private LocalDateTime created;

    @OneToMany(mappedBy = "user")
    List<Task> tasks;

    @PrePersist
    protected void onCreate() {
        this.created = LocalDateTime.now();
    }


    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }


}

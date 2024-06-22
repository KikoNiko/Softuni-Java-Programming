package com.plannerapp.model.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "priorities")
public class Priority extends BaseEntity{
    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private PriorityName name;
    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "priority")
    List<Task> tasks;

    public Priority() {
        this.tasks = new ArrayList<>();
    }

    public Priority(PriorityName name, String description) {
        this();
        this.name = name;
        this.description = description;
    }

    public PriorityName getName() {
        return name;
    }

    public void setName(PriorityName name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}

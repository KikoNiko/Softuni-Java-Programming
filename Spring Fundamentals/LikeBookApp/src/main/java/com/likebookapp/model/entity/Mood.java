package com.likebookapp.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "moods")
public class Mood extends BaseEntity{

    private String description;
    @Column(unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private MoodName name;

    public Mood() {
    }

    public Mood(MoodName name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MoodName getName() {
        return name;
    }

    public void setName(MoodName name) {
        this.name = name;
    }
}

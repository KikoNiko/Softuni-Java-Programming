package com.dictionaryapp.model.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "languages")
public class Language extends BaseEntity{
    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private LanguageEnum name;
    @Column(nullable = false)
    private String description;
    @OneToMany(mappedBy = "language")
    private List<Word> words;

    public Language() {
        this.words = new ArrayList<>();
    }
    public Language(LanguageEnum name, String description) {
        this.name = name;
        this.description = description;
        this.words = new ArrayList<>();
    }

    public LanguageEnum getName() {
        return name;
    }

    public void setName(LanguageEnum name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Word> getWords() {
        return words;
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }
}

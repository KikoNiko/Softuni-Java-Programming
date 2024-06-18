package com.dictionaryapp.model.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class WordAddDTO {
    @Size(min = 2, max = 40)
    @NotBlank
    private String term;
    @Size(min = 2, max = 80)
    @NotBlank
    private String translation;
    @Size(min = 2, max = 200)
    private String example;
    @PastOrPresent
    @NotNull
    private LocalDate inputDate;
    @NotBlank
    private String language;

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public LocalDate getInputDate() {
        return inputDate;
    }

    public void setInputDate(LocalDate inputDate) {
        this.inputDate = inputDate;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}

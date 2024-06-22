package com.likebookapp.model.dto;

import com.likebookapp.model.entity.MoodName;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddPostDTO {
    @Size(min = 2, max = 50, message = "Content length must be between 2 and 50 characters!")
    @NotEmpty
    private String content;

    @NotNull(message = "You must select a mood!")
    private MoodName mood;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MoodName getMood() {
        return mood;
    }

    public void setMood(MoodName mood) {
        this.mood = mood;
    }
}

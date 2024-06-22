package com.likebookapp.model.dto;

import com.likebookapp.model.entity.MoodName;
import com.likebookapp.model.entity.Post;

public class PostInfoDTO {
    private long id;
    private String content;

    private String createdBy;

    private MoodName moodName;

    private int likes;

    public PostInfoDTO(Post post) {
        this.id = post.getId();
        this.content = post.getContent();
        this.createdBy = post.getUser().getUsername();
        this.moodName = post.getMood().getName();
        this.likes = post.getUserLikes().size();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public MoodName getMoodName() {
        return moodName;
    }

    public void setMoodName(MoodName moodName) {
        this.moodName = moodName;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}

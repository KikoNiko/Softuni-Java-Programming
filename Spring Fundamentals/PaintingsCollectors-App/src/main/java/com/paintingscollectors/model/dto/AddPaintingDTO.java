package com.paintingscollectors.model.dto;

import com.paintingscollectors.model.entity.StyleName;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddPaintingDTO {
    @Size(min = 5, max = 40, message = "Name length must be between 5 and 40 characters!")
    @NotBlank
    private String name;
    @Size(min = 5, max = 30, message = "Author name must be between 5 and 30 characters!")
    @NotBlank
    private String author;
    @Size(max = 150, message = "Image URl cannot be longer than 150 characters!")
    @NotBlank(message = "Please enter valid image URL!")
    private String imgUrl;
    @NotNull(message = "You must select a style!")
    private StyleName styleName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public StyleName getStyleName() {
        return styleName;
    }

    public void setStyleName(StyleName styleName) {
        this.styleName = styleName;
    }
}

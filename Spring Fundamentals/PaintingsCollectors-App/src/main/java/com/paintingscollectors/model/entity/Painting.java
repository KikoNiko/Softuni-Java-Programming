package com.paintingscollectors.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "paintings")
public class Painting extends BaseEntity {
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String author;
    @ManyToOne
    private Style style;
    @ManyToOne
    private User owner;
    @Column(nullable = false)
    private String imgUrl;
    @Column(nullable = false)
    private boolean isFavorite;
    private int votes;

    public Painting() {
        this.isFavorite = false;
        this.votes = 0;
    }

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

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Painting painting = (Painting) o;
        return isFavorite == painting.isFavorite
                && votes == painting.votes
                && Objects.equals(name, painting.name)
                && Objects.equals(author, painting.author)
                && Objects.equals(style, painting.style)
                && Objects.equals(owner, painting.owner)
                && Objects.equals(imgUrl, painting.imgUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, author, style, owner, imgUrl, isFavorite, votes);
    }
}


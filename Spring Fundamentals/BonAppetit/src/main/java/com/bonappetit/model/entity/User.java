package com.bonappetit.model.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User extends BaseEntity{
    @Column(unique = true, nullable = false)
    private String username;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "addedBy")
    private List<Recipe> addedRecipes;

    @ManyToMany
    private List<Recipe> favouriteRecipes;

    public User() {
        this.addedRecipes = new ArrayList<>();
        this.favouriteRecipes = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Recipe> getAddedRecipes() {
        return addedRecipes;
    }

    public void setAddedRecipes(List<Recipe> addedRecipes) {
        this.addedRecipes = addedRecipes;
    }

    public List<Recipe> getFavouriteRecipes() {
        return favouriteRecipes;
    }

    public void setFavouriteRecipes(List<Recipe> favouriteRecipes) {
        this.favouriteRecipes = favouriteRecipes;
    }
}

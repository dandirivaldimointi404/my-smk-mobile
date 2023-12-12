package com.example.my_smk_app.Models;

import com.google.gson.annotations.SerializedName;

public class UserModel {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("username")
    private String username;

    @SerializedName("password")
    private String password;


    @SerializedName("level")
    private String level;

    @SerializedName("avatar")
    private String avatar;

    @SerializedName("email_verified_at")
    private String emailVerifiedAt;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("updated_at")
    private String updatedAt;

    public UserModel(int id, String name, String username,String password, String level, String avatar, String emailVerifiedAt, String createdAt) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.level = level;
        this.avatar = avatar;
        this.emailVerifiedAt = emailVerifiedAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getLevel() {
        return level;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getEmailVerifiedAt() {
        return emailVerifiedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setEmailVerifiedAt(String emailVerifiedAt) {
        this.emailVerifiedAt = emailVerifiedAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}


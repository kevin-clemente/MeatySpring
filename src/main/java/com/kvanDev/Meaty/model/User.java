package com.kvanDev.Meaty.model;

import jakarta.persistence.*;

@Entity
@Table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String password;
    private String salt;
    private String username;
    private String email;

    public User() {
    }

    public User(String password, String salt, String username, String email) {
        this.password = password;
        this.salt = salt;
        this.username = username;
        this.email = email;
    }

    public User(Long userId, String password, String salt, String username, String email) {
        this.userId = userId;
        this.password = password;
        this.salt = salt;
        this.username = username;
        this.email = email;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
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
}

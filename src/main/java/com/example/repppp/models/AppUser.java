package com.example.repppp.models;

public class AppUser {
    private Long id;
    private String username;
    private String passwordHash;
    private Integer reputation;

    public AppUser() {
    }

    public AppUser(Long id, String username, String passwordHash, Integer reputation) {
        this.id = id;
        this.username = username;
        this.passwordHash = passwordHash;
        this.reputation = reputation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Integer getReputation() {
        return reputation;
    }

    public void setReputation(Integer reputation) {
        this.reputation = reputation;
    }
}
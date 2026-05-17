package com.example.repppp.dto.response;

public class UserResponse {

    private Long id;
    private String username;
    private Integer reputation;

    public UserResponse() {
    }

    public UserResponse(Long id, String username, Integer reputation) {
        this.id = id;
        this.username = username;
        this.reputation = reputation;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Integer getReputation() {
        return reputation;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setReputation(Integer reputation) {
        this.reputation = reputation;
    }
}
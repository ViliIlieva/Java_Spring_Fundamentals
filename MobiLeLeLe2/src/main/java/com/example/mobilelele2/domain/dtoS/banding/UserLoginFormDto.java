package com.example.mobilelele2.domain.dtoS.banding;

public class UserLoginFormDto {

    private String username; // –  username of the user.

    private String password;

    public UserLoginFormDto() {
    }

    public UserLoginFormDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public UserLoginFormDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserLoginFormDto setPassword(String password) {
        this.password = password;
        return this;
    }
}

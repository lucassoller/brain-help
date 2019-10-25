package com.example.app.classes.dto;

public class LoginResponseDto {

    private String email;
    private String token;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LoginResponseDto(String email, String token) {
        super();
        this.email = email;
        this.token = token;
    }

    public LoginResponseDto() {
        super();
        // TODO Auto-generated constructor stub
    }
}

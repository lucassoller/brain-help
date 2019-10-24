package com.example.app.classes;

import java.util.Optional;

public class Usuario {

    private String email;

    private String senha;

    public Optional<String> getRole() {
        return Optional.of("Usuario normal");
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public Usuario(String email, String senha) {
        super();
        this.email = email;
        this.senha = senha;
    }
    public Usuario() {
        super();
        // TODO Auto-generated constructor stub
    }

}

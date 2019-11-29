package com.example.app.classes.dto;

public class RedefinicaoSenhaRequestDto {

    private String email;
    private String senha;
    private String senhaAtual;

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

    public String getSenhaAtual() {
        return senhaAtual;
    }

    public void setSenhaAtual(String senhaAtual) {
        this.senhaAtual = senhaAtual;
    }

    public RedefinicaoSenhaRequestDto(String email, String senha, String senhaAtual) {
        super();
        this.email = email;
        this.senha = senha;
        this.senhaAtual = senhaAtual;
    }

    public RedefinicaoSenhaRequestDto() {
        super();
        // TODO Auto-generated constructor stub
    }
}

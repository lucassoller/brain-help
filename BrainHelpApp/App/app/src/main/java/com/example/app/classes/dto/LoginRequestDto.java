package com.example.app.classes.dto;

public class LoginRequestDto {

    private String identificacao;
    private String senha;

    public String getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LoginRequestDto(String identificacao, String senha) {
        super();
        this.identificacao = identificacao;
        this.senha = senha;
    }

    public LoginRequestDto() {
        super();
        // TODO Auto-generated constructor stub
    }
}

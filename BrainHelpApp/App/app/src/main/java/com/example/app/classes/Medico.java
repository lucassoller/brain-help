package com.example.app.classes;


import java.io.Serializable;
import java.util.List;

public class Medico implements Serializable {

    private String nome;

    private String sobrenome;

    private String email;

    private String senha;

    private boolean google;

    private String telefone;

    private String foto;

    private Endereco endereco;

    private String especializacao;

    private List<Diagnosticado> diagnosticados;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public List<Diagnosticado> getDiagnosticados() {
        return diagnosticados;
    }

    public void setDiagnosticados(List<Diagnosticado> diagnosticados) {
        this.diagnosticados = diagnosticados;
    }

    public boolean isGoogle() {
        return google;
    }

    public void setGoogle(boolean google) {
        this.google = google;
    }


    public String getSobrenome() {
        return sobrenome;
    }


    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }


    public Endereco getEndereco() {
        return endereco;
    }


    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }


    public String getEspecializacao() {
        return especializacao;
    }


    public void setEspecializacao(String especializacao) {
        this.especializacao = especializacao;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Medico(String email, String senha, String nome, String sobrenome,
                  boolean google, String telefone, Endereco endereco, String especializacao,
                  List<Diagnosticado> diagnosticados, String foto) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.senha = senha;
        this.google = google;
        this.telefone = telefone;
        this.endereco = endereco;
        this.especializacao = especializacao;
        this.diagnosticados = diagnosticados;
        this.foto = foto;
    }

    public Medico() {
        super();
        // TODO Auto-generated constructor stub
    }
}
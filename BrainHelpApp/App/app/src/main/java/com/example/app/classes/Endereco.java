package com.example.app.classes;

import java.io.Serializable;

public class Endereco implements Serializable {

    private Integer codEndereco;
    private String logradouro;
    private String cidade;
    private String estado;
    private int numero;
    private String bairro;
    private String cep;
    private String foto;
    private String titulo;
    private String descricao;

    public Integer getCodEndereco() {
        return codEndereco;
    }

    public void setCodEndereco(Integer codEndereco) {
        this.codEndereco = codEndereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Endereco(Integer codEndereco, String logradouro, String cidade, String estado, int numero,
                    String bairro, String cep, String foto, String titulo, String descricao) {
        super();
        this.codEndereco = codEndereco;
        this.logradouro = logradouro;
        this.cidade = cidade;
        this.estado = estado;
        this.numero = numero;
        this.bairro = bairro;
        this.cep = cep;
        this.foto = foto;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public Endereco() {
        super();
        // TODO Auto-generated constructor stub
    }
}
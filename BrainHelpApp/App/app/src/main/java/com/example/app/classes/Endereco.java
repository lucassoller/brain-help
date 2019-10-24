package com.example.app.classes;

public class Endereco {

    private Integer codEndereco;

    private String endereco;

    private String cidade;

    private String estado;

    private String pais;

    private int numero;

    private String bairro;

    private String cep;

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

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Endereco(Integer codEndereco, String endereco, String cidade, String estado, String pais, int numero,
                    String bairro, String cep) {
        super();
        this.codEndereco = codEndereco;
        this.endereco = endereco;
        this.cidade = cidade;
        this.estado = estado;
        this.pais = pais;
        this.numero = numero;
        this.bairro = bairro;
        this.cep = cep;
    }

    public Endereco() {
        super();
        // TODO Auto-generated constructor stub
    }

}

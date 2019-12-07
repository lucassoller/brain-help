package com.example.app.model;

import com.example.app.model.enumerated.Sexo;
import java.io.Serializable;

public class Vinculo implements Serializable {

    private Integer codVinculo;
    private String nome;
    private String telefone;
    private String sobrenome;
    private String vinculo;
    private String lembrancas;
    private Sexo sexo;
    private int idade;
    private boolean contatoEmergencia;
    private Endereco endereco;
    private Diagnosticado diagnosticado;
    private Fotografia fotografia;
    private String foto;

    public Integer getCodVinculo() {
        return codVinculo;
    }

    public void setCodVinculo(Integer codVinculo) {
        this.codVinculo = codVinculo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getVinculo() {
        return vinculo;
    }

    public void setVinculo(String vinculo) {
        this.vinculo = vinculo;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public Diagnosticado getDiagnosticado() {
        return diagnosticado;
    }

    public void setDiagnosticado(Diagnosticado diagnosticado) {
        this.diagnosticado = diagnosticado;
    }

    public String getLembrancas() {
        return lembrancas;
    }

    public void setLembrancas(String lembrancas) {
        this.lembrancas = lembrancas;
    }

    public boolean isContatoEmergencia() {
        return contatoEmergencia;
    }

    public void setContatoEmergencia(boolean contatoEmergencia) {
        this.contatoEmergencia = contatoEmergencia;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Fotografia getFotografia() {
        return fotografia;
    }

    public void setFotografia(Fotografia fotografia) {
        this.fotografia = fotografia;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Vinculo(Integer codVinculo, String nome, String telefone, String sobrenome, String vinculo, Sexo sexo,
                   int idade, boolean contatoEmergencia, Endereco endereco, String lembrancas,
                   Diagnosticado diagnosticado, Fotografia fotografia, String foto) {
        super();
        this.codVinculo = codVinculo;
        this.nome = nome;
        this.telefone = telefone;
        this.sobrenome = sobrenome;
        this.vinculo = vinculo;
        this.sexo = sexo;
        this.idade = idade;
        this.contatoEmergencia = contatoEmergencia;
        this.endereco = endereco;
        this.lembrancas = lembrancas;
        this.diagnosticado = diagnosticado;
        this.fotografia = fotografia;
        this.foto = foto;
    }

    public Vinculo() {
        super();
        // TODO Auto-generated constructor stub
    }
}

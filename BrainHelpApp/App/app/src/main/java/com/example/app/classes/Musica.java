package com.example.app.classes;

import java.io.Serializable;
import java.util.List;

public class Musica implements Serializable {

    private Integer codMusica;

    private String nome;

    private String cantor;

    private List<Lembranca> lembrancas;

    private Diagnosticado diagnosticado;

    public Integer getCodMusica() {
        return codMusica;
    }

    public void setCodMusica(Integer codMusica) {
        this.codMusica = codMusica;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCantor() {
        return cantor;
    }

    public void setCantor(String cantor) {
        this.cantor = cantor;
    }

    public List<Lembranca> getLembrancas() {
        return lembrancas;
    }

    public void setLembrancas(List<Lembranca> lembrancas) {
        this.lembrancas = lembrancas;
    }

    public Diagnosticado getDiagnosticado() {
        return diagnosticado;
    }

    public void setDiagnosticado(Diagnosticado diagnosticado) {
        this.diagnosticado = diagnosticado;
    }

    public Musica(Integer codMusica, String nome, String cantor, List<Lembranca> lembrancas,
                  Diagnosticado diagnosticado) {
        super();
        this.codMusica = codMusica;
        this.nome = nome;
        this.cantor = cantor;
        this.lembrancas = lembrancas;
        this.diagnosticado = diagnosticado;
    }

    public Musica() {
        super();
        // TODO Auto-generated constructor stub
    }
}

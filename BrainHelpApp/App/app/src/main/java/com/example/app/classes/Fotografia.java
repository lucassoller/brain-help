package com.example.app.classes;

import java.io.Serializable;
import java.util.Date;

public class Fotografia   implements Serializable {

    private Integer codFotografia;

    private String lugar;

    private Date data;

    private String lembrancas;

    private Diagnosticado diagnosticado;

    private String foto;

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Integer getCodFotografia() {
        return codFotografia;
    }

    public void setCodFotografia(Integer codFotografia) {
        this.codFotografia = codFotografia;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getLembrancas() {
        return lembrancas;
    }

    public void setLembrancas(String lembrancas) {
        this.lembrancas = lembrancas;
    }

    public Diagnosticado getDiagnosticado() {
        return diagnosticado;
    }

    public void setDiagnosticado(Diagnosticado diagnosticado) {
        this.diagnosticado = diagnosticado;
    }

    public Fotografia(Integer codFotografia, String lugar, Date data, String lembrancas, Diagnosticado diagnosticado, String foto) {
        this.codFotografia = codFotografia;
        this.lugar = lugar;
        this.data = data;
        this.lembrancas = lembrancas;
        this.diagnosticado = diagnosticado;
        this.foto = foto;
    }

    public Fotografia() {
        super();
        // TODO Auto-generated constructor stub
    }

}

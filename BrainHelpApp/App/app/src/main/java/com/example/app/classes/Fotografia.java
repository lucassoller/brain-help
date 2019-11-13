package com.example.app.classes;

import java.util.Date;
import java.util.List;

public class Fotografia  {

    private Integer codFotografia;

    private String lugar;

    private String descricao;

    private Date data;

    private List<Vinculo> vinculos;

    private List<Lembranca> lembrancas;

    private Diagnosticado diagnosticado;

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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public List<Vinculo> getVinculos() {
        return vinculos;
    }

    public void setVinculos(List<Vinculo> vinculos) {
        this.vinculos = vinculos;
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

    public Fotografia(Integer codFotografia, String lugar, String descricao, Date data, List<Vinculo> vinculos,
                      List<Lembranca> lembrancas, Diagnosticado diagnosticado) {
        super();
        this.codFotografia = codFotografia;
        this.lugar = lugar;
        this.descricao = descricao;
        this.data = data;
        this.vinculos = vinculos;
        this.lembrancas = lembrancas;
        this.diagnosticado = diagnosticado;
    }

    public Fotografia() {
        super();
        // TODO Auto-generated constructor stub
    }

}

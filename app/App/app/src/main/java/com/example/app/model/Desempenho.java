package com.example.app.model;

import com.example.app.model.enumerated.AvaliacaoDesempenho;
import java.io.Serializable;
import java.util.Date;

public class Desempenho implements Serializable {

    private Integer codDesempenho;

    private String atividade;

    private int pontuacao;

    private Date dataRealizacao;

    private AvaliacaoDesempenho avaliacaoDesempenho;

    private Diagnosticado diagnosticado;

    public Integer getCodDesempenho() {
        return codDesempenho;
    }

    public void setCodDesempenho(Integer codDesempenho) {
        this.codDesempenho = codDesempenho;
    }

    public String getAtividade() {
        return atividade;
    }

    public void setAtividade(String atividade) {
       this.atividade = atividade;
    }

    public Date getDataRealizacao() {
        return dataRealizacao;
    }

    public void setDataRealizacao(Date dataRealizacao) {
        this.dataRealizacao = dataRealizacao;
    }

    public AvaliacaoDesempenho getAvaliacaoDesempenho() {
        return avaliacaoDesempenho;
    }

    public void setAvaliacaoDesempenho(AvaliacaoDesempenho avaliacaoDesempenho) {
        this.avaliacaoDesempenho = avaliacaoDesempenho;
    }

    public Diagnosticado getDiagnosticado() {
        return diagnosticado;
    }

    public void setDiagnosticado(Diagnosticado diagnosticado) {
        this.diagnosticado = diagnosticado;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public Desempenho(Integer codDesempenho, String atividade, int pontuacao, Date dataRealizacao,
                      AvaliacaoDesempenho avaliacaoDesempenho, Diagnosticado diagnosticado) {
        super();
        this.codDesempenho = codDesempenho;
        this.atividade = atividade;
        this.pontuacao = pontuacao;
        this.dataRealizacao = dataRealizacao;
        this.avaliacaoDesempenho = avaliacaoDesempenho;
        this.diagnosticado = diagnosticado;
    }

    public Desempenho() {
        super();
        // TODO Auto-generated constructor stub
    }
}

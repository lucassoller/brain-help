package com.example.app.classes;

import com.example.app.enumerated.AvaliacaoDesempenho;

import java.io.Serializable;
import java.util.Date;

public class Desempenho implements Serializable {

    private Integer codDesempenho;

    private Atividade atividade;

    private int pontuacao;

    private Date data;

    private AvaliacaoDesempenho avaliacaoDesempenho;

    private Diagnosticado diagnosticado;

    public Integer getCodDesempenho() {
        return codDesempenho;
    }

    public void setCodDesempenho(Integer codDesempenho) {
        this.codDesempenho = codDesempenho;
    }

    public Atividade getAtividade() {
        return atividade;
    }

    public void setAtividade(Atividade atividade) {
       this.atividade = atividade;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
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

    public Desempenho(Integer codDesempenho, Atividade atividade, int pontuacao, Date data,
                      AvaliacaoDesempenho avaliacaoDesempenho, Diagnosticado diagnosticado) {
        super();
        this.codDesempenho = codDesempenho;
        this.atividade = atividade;
        this.pontuacao = pontuacao;
        this.data = data;
        this.avaliacaoDesempenho = avaliacaoDesempenho;
        this.diagnosticado = diagnosticado;
    }

    public Desempenho() {
        super();
        // TODO Auto-generated constructor stub
    }
}

package com.example.app.classes;

import com.example.app.enumerated.StatusTarefa;

import java.util.Date;

public class Tarefa  {

    private Integer codTarefa;

    private String tarefa;

    private String descricao;

    private Date dataRealizacao;

    private StatusTarefa statusTarefa;

    private Diagnosticado diagnosticado;

    public Integer getCodTarefa() {
        return codTarefa;
    }

    public void setCodTarefa(Integer codTarefa) {
        this.codTarefa = codTarefa;
    }

    public String getTarefa() {
        return tarefa;
    }

    public void setTarefa(String tarefa) {
        this.tarefa = tarefa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataRealizacao() {
        return dataRealizacao;
    }

    public void setDataRealizacao(Date dataRealizacao) {
        this.dataRealizacao = dataRealizacao;
    }

    public Diagnosticado getDiagnosticado() {
        return diagnosticado;
    }

    public void setDiagnosticado(Diagnosticado diagnosticado) {
        this.diagnosticado = diagnosticado;
    }

    public StatusTarefa getStatusTarefa() {
        return statusTarefa;
    }

    public void setStatusTarefa(StatusTarefa statusTarefa) {
        this.statusTarefa = statusTarefa;
    }

    public Tarefa(Integer codTarefa, String tarefa, String descricao, Date dataRealizacao, StatusTarefa statusTarefa,
                  Diagnosticado diagnosticado) {
        super();
        this.codTarefa = codTarefa;
        this.tarefa = tarefa;
        this.descricao = descricao;
        this.dataRealizacao = dataRealizacao;
        this.statusTarefa = statusTarefa;
        this.diagnosticado = diagnosticado;
    }

    public Tarefa() {
        super();
        // TODO Auto-generated constructor stub
    }
}

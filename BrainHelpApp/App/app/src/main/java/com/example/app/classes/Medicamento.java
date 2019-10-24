package com.example.app.classes;

import com.example.app.enumm.Medida;
import com.example.app.enumm.TipoDuracao;

import java.util.Date;

public class Medicamento {

    private Integer codMedicamento;

    private String nomeMedicamento;

    private String funcao;

    private String contraIndicacoes;

    private String efeitosColaterais;

    private int quantidade;

    private Medida medida;

    private int frequenciaDiaria;

    private Date proximoHorario;

    private int duracao;

    private TipoDuracao tipoDuracao;

    private Diagnosticado diagnosticado;

    public Integer getCodMedicamento() {
        return codMedicamento;
    }

    public void setCodMedicamento(Integer codMedicamento) {
        this.codMedicamento = codMedicamento;
    }

    public String getNomeMedicamento() {
        return nomeMedicamento;
    }

    public void setNomeMedicamento(String nomeMedicamento) {
        this.nomeMedicamento = nomeMedicamento;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public String getContraIndicacoes() {
        return contraIndicacoes;
    }

    public void setContraIndicacoes(String contraIndicacoes) {
        this.contraIndicacoes = contraIndicacoes;
    }

    public String getEfeitosColaterais() {
        return efeitosColaterais;
    }

    public void setEfeitosColaterais(String efeitosColaterais) {
        this.efeitosColaterais = efeitosColaterais;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Medida getMedida() {
        return medida;
    }

    public void setMedida(Medida medida) {
        this.medida = medida;
    }

    public int getFrequenciaDiaria() {
        return frequenciaDiaria;
    }

    public void setFrequenciaDiaria(int frequenciaDiaria) {
        this.frequenciaDiaria = frequenciaDiaria;
    }

    public Date getProximoHorario() {
        return proximoHorario;
    }

    public void setProximoHorario(Date proximoHorario) {
        this.proximoHorario = proximoHorario;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public Diagnosticado getDiagnosticado() {
        return diagnosticado;
    }

    public void setDiagnosticado(Diagnosticado diagnosticado) {
        this.diagnosticado = diagnosticado;
    }

    public TipoDuracao getTipoDuracao() {
        return tipoDuracao;
    }

    public void setTipoDuracao(TipoDuracao tipoDuracao) {
        this.tipoDuracao = tipoDuracao;
    }

    public Medicamento(Integer codMedicamento, String nomeMedicamento, String funcao, String contraIndicacoes,
                       String efeitosColaterais, int quantidade, Medida medida, int frequenciaDiaria, Date proximoHorario,
                       int duracao, TipoDuracao tipoDuracao, Diagnosticado diagnosticado) {
        super();
        this.codMedicamento = codMedicamento;
        this.nomeMedicamento = nomeMedicamento;
        this.funcao = funcao;
        this.contraIndicacoes = contraIndicacoes;
        this.efeitosColaterais = efeitosColaterais;
        this.quantidade = quantidade;
        this.medida = medida;
        this.frequenciaDiaria = frequenciaDiaria;
        this.proximoHorario = proximoHorario;
        this.duracao = duracao;
        this.tipoDuracao = tipoDuracao;
        this.diagnosticado = diagnosticado;
    }

    public Medicamento() {
        super();
        // TODO Auto-generated constructor stub
    }
}

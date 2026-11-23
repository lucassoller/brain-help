package com.example.app.model;

import com.example.app.model.enumerated.Frequencia;
import com.example.app.model.enumerated.Medida;
import java.io.Serializable;
import java.util.Date;

public class Medicamento implements Serializable {

    private Integer codMedicamento;
    private String nomeMedicamento;
    private String funcao;
    private String contraIndicacoes;
    private String efeitosColaterais;
    private int quantidade;
    private Medida medida;
    private Date horario;
    private Frequencia frequencia;
    private Diagnosticado diagnosticado;
    private String foto;

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

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

    public Date getHorario() {
        return horario;
    }

    public void setHorario(Date horario) {
        this.horario = horario;
    }

    public Diagnosticado getDiagnosticado() {
        return diagnosticado;
    }

    public void setDiagnosticado(Diagnosticado diagnosticado) {
        this.diagnosticado = diagnosticado;
    }
    public Frequencia getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(Frequencia frequencia) {
        this.frequencia = frequencia;
    }

    public Medicamento(Integer codMedicamento, String nomeMedicamento, String funcao, String contraIndicacoes, String efeitosColaterais, int quantidade, Medida medida, Date horario, Frequencia frequencia, Diagnosticado diagnosticado, String foto) {
        this.codMedicamento = codMedicamento;
        this.nomeMedicamento = nomeMedicamento;
        this.funcao = funcao;
        this.contraIndicacoes = contraIndicacoes;
        this.efeitosColaterais = efeitosColaterais;
        this.quantidade = quantidade;
        this.medida = medida;
        this.horario = horario;
        this.frequencia = frequencia;
        this.diagnosticado = diagnosticado;
        this.foto = foto;
    }

    public Medicamento() {
        super();
        // TODO Auto-generated constructor stub
    }
}

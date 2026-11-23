package com.example.app.model;

import com.example.app.model.enumerated.TipoAtividade;
import java.io.Serializable;

public class Atividade implements Serializable {

    private Integer codAtividade;
    private String nome;
    private String descricao;
    private int pontuacaoTotal;
    private TipoAtividade tipoAtividade;

    public Integer getCodAtividade() {
        return codAtividade;
    }

    public void setCodAtividade(Integer codAtividade) {
        this.codAtividade = codAtividade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getPontuacaoTotal() {
        return pontuacaoTotal;
    }

    public void setPontuacaoTotal(int pontuacaoTotal) {
        this.pontuacaoTotal = pontuacaoTotal;
    }

    public TipoAtividade getTipoAtividade() {
        return tipoAtividade;
    }

    public void setTipoAtividade(TipoAtividade tipoAtividade) {
        this.tipoAtividade = tipoAtividade;
    }

    public Atividade(Integer codAtividade, String nome, String descricao, int pontuacaoTotal,
                     TipoAtividade tipoAtividade) {
        super();
        this.codAtividade = codAtividade;
        this.nome = nome;
        this.descricao = descricao;
        this.pontuacaoTotal = pontuacaoTotal;
        this.tipoAtividade = tipoAtividade;
    }

    public Atividade() {
        super();
        // TODO Auto-generated constructor stub
    }
}

package com.example.demo.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.example.demo.model.enumerated.TipoAtividade;

@Entity
public class Atividade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codAtividade;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private String descricao;
	
	@Column(nullable = false)
	private int pontuacaoTotal;
	
	@Column(nullable = false)
	private TipoAtividade tipoAtividade;
	
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "atividade")
	private List<Desempenho> desempenhos;

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

	public List<Desempenho> getDesempenhos() {
		return desempenhos;
	}

	public void setDesempenhos(List<Desempenho> desempenhos) {
		this.desempenhos = desempenhos;
	}

	public Atividade(Integer codAtividade, String nome, String descricao, int pontuacaoTotal,
			TipoAtividade tipoAtividade, List<Desempenho> desempenhos) {
		super();
		this.codAtividade = codAtividade;
		this.nome = nome;
		this.descricao = descricao;
		this.pontuacaoTotal = pontuacaoTotal;
		this.tipoAtividade = tipoAtividade;
		this.desempenhos = desempenhos;
	}

	public Atividade() {
		super();
		// TODO Auto-generated constructor stub
	}
}

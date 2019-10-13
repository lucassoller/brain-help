package com.example.demo.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import com.example.demo.model.enumerated.AvaliacaoDesempenho;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Desempenho {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codDesempenho;
	
	@OneToOne
	@JoinColumn(name="codAtividade")
	private Atividade atividade;
	
	@Column(nullable = false)
	private int pontuacao;
	
	@Column(nullable = false)
	private Date data;
	
	@Column(nullable = false)
	private AvaliacaoDesempenho avaliacaoDesempenho;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "codDiagnosticado")
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

package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Teste{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codDesempenho;
	
	@Column(nullable = false)
	private String dataRealizacao;
	

	public Integer getCodDesempenho() {
		return codDesempenho;
	}

	public void setCodDesempenho(Integer codDesempenho) {
		this.codDesempenho = codDesempenho;
	}



	public String getDataRealizacao() {
		return dataRealizacao;
	}

	public void setDataRealizacao(String dataRealizacao) {
		this.dataRealizacao = dataRealizacao;
	}

	public Teste(Integer codDesempenho, String dataRealizacao) {
		super();
		this.codDesempenho = codDesempenho;
		this.dataRealizacao = dataRealizacao;
	}

	public Teste() {
		super();
		// TODO Auto-generated constructor stub
	}
}

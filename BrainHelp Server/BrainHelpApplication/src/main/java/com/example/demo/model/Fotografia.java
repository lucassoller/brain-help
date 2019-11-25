package com.example.demo.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Fotografia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codFotografia;
	
	@Column(nullable = false)
	private String lugar;
	
	@Column(nullable = false)
	private String descricao;
	
	private Date data;
	
	private String lembrancas;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "codDiagnosticado")
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

	public Diagnosticado getDiagnosticado() {
		return diagnosticado;
	}

	public void setDiagnosticado(Diagnosticado diagnosticado) {
		this.diagnosticado = diagnosticado;
	}

	public String getLembrancas() {
		return lembrancas;
	}

	public void setLembrancas(String lembrancas) {
		this.lembrancas = lembrancas;
	}

	public Fotografia(Integer codFotografia, String lugar, String descricao, Date data,
			String lembrancas, Diagnosticado diagnosticado) {
		super();
		this.codFotografia = codFotografia;
		this.lugar = lugar;
		this.descricao = descricao;
		this.data = data;
		this.lembrancas = lembrancas;
		this.diagnosticado = diagnosticado;
	}

	public Fotografia() {
		super();
		// TODO Auto-generated constructor stub
	}

}

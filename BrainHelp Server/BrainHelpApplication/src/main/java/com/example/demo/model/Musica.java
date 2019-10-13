package com.example.demo.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Musica {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codMusica;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private String cantor;
	
	@OneToMany(mappedBy = "musica")
	private List<Lembranca> lembrancas;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "codDiagnosticado")
	private Diagnosticado diagnosticado;

	public Integer getCodMusica() {
		return codMusica;
	}

	public void setCodMusica(Integer codMusica) {
		this.codMusica = codMusica;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCantor() {
		return cantor;
	}

	public void setCantor(String cantor) {
		this.cantor = cantor;
	}

	public List<Lembranca> getLembrancas() {
		return lembrancas;
	}

	public void setLembrancas(List<Lembranca> lembrancas) {
		this.lembrancas = lembrancas;
	}

	public Diagnosticado getDiagnosticado() {
		return diagnosticado;
	}

	public void setDiagnosticado(Diagnosticado diagnosticado) {
		this.diagnosticado = diagnosticado;
	}

	public Musica(Integer codMusica, String nome, String cantor, List<Lembranca> lembrancas,
			Diagnosticado diagnosticado) {
		super();
		this.codMusica = codMusica;
		this.nome = nome;
		this.cantor = cantor;
		this.lembrancas = lembrancas;
		this.diagnosticado = diagnosticado;
	}

	public Musica() {
		super();
		// TODO Auto-generated constructor stub
	}	
}

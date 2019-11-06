package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.example.demo.model.enumerated.TipoAtividade;

@Entity
public class Arquivo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codArquivo;
	
	@Column(nullable = false)
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getCodArquivo() {
		return codArquivo;
	}

	public void setCodArquivo(Integer codArquivo) {
		this.codArquivo = codArquivo;
	}

	public Arquivo(Integer codArquivo, String nome) {
		super();
		this.codArquivo = codArquivo;
		this.nome = nome;
	}

	public Arquivo() {
		super();
		// TODO Auto-generated constructor stub
	}
}

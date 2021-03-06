package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codEndereco;
	
	@Column(nullable = false)
	private String logradouro;
	
	@Column(nullable = false)
	private String cidade;
	
	@Column(nullable = false)
	private String estado;
	
	@Column(nullable = false)
	private int numero;
	
	@Column(nullable = false)
	private String bairro;
	
	private String cep;
	
	@Lob
	@Column(length = 2000)
	private String foto;
	
	private String titulo;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "codDiagnosticado")
	private Diagnosticado diagnosticado;

	public Integer getCodEndereco() {
		return codEndereco;
	}

	public void setCodEndereco(Integer codEndereco) {
		this.codEndereco = codEndereco;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Diagnosticado getDiagnosticado() {
		return diagnosticado;
	}

	public void setDiagnosticado(Diagnosticado diagnosticado) {
		this.diagnosticado = diagnosticado;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Endereco(Integer codEndereco, String logradouro, String cidade, String estado, int numero, String bairro,
			String cep, String foto, String titulo, Diagnosticado diagnosticado) {
		super();
		this.codEndereco = codEndereco;
		this.logradouro = logradouro;
		this.cidade = cidade;
		this.estado = estado;
		this.numero = numero;
		this.bairro = bairro;
		this.cep = cep;
		this.foto = foto;
		this.titulo = titulo;
		this.diagnosticado = diagnosticado;
	}

	public Endereco() {
		super();
		// TODO Auto-generated constructor stub
	}
}
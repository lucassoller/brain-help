package com.example.demo.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import com.example.demo.model.enumerated.Sexo;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Vinculo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codVinculo;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
    private String telefone;
	
	@Column(nullable = false)
	private String sobrenome;
	
	@Column(nullable = false)
	private String vinculo;
	
	@Column(nullable = false)
	private Sexo sexo;
	
	@Column(nullable = false)
	private int idade;
		
	@OneToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name="codEndereco")
	private Endereco endereco;
	
	private String lembrancas;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "codDiagnosticado")
	private Diagnosticado diagnosticado;
	
	private String foto;

	public Integer getCodVinculo() {
		return codVinculo;
	}

	public void setCodVinculo(Integer codVinculo) {
		this.codVinculo = codVinculo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getVinculo() {
		return vinculo;
	}

	public void setVinculo(String vinculo) {
		this.vinculo = vinculo;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getLembrancas() {
		return lembrancas;
	}

	public void setLembrancas(String lembrancas) {
		this.lembrancas = lembrancas;
	}

	public Diagnosticado getDiagnosticado() {
		return diagnosticado;
	}

	public void setDiagnosticado(Diagnosticado diagnosticado) {
		this.diagnosticado = diagnosticado;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Vinculo(Integer codVinculo, String nome, String telefone, String sobrenome, String vinculo, Sexo sexo,
			int idade, boolean contatoEmergencia, Endereco endereco, String lembrancas,
			Diagnosticado diagnosticado, String foto) {
		super();
		this.codVinculo = codVinculo;
		this.nome = nome;
		this.telefone = telefone;
		this.sobrenome = sobrenome;
		this.vinculo = vinculo;
		this.sexo = sexo;
		this.idade = idade;
		this.endereco = endereco;
		this.lembrancas = lembrancas;
		this.diagnosticado = diagnosticado;
		this.foto = foto;
	}

	public Vinculo() {
		super();
		// TODO Auto-generated constructor stub
	}
}

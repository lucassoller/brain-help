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
	
	private boolean contatoEmergencia;
	
	@OneToOne
	@JoinColumn(name="codEndereco")
	private Endereco endereco;
	
	@OneToMany(mappedBy = "vinculo")
	private List<Lembranca> lembrancas;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "codDiagnosticado")
	private Diagnosticado diagnosticado;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "codFotografia")
	private Fotografia fotografia;

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

	public boolean isContatoEmergencia() {
		return contatoEmergencia;
	}

	public void setContatoEmergencia(boolean contatoEmergencia) {
		this.contatoEmergencia = contatoEmergencia;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Fotografia getFotografia() {
		return fotografia;
	}

	public void setFotografia(Fotografia fotografia) {
		this.fotografia = fotografia;
	}

	public Vinculo(Integer codVinculo, String nome, String telefone, String sobrenome, String vinculo, Sexo sexo,
			int idade, boolean contatoEmergencia, Endereco endereco, List<Lembranca> lembrancas,
			Diagnosticado diagnosticado, Fotografia fotografia) {
		super();
		this.codVinculo = codVinculo;
		this.nome = nome;
		this.telefone = telefone;
		this.sobrenome = sobrenome;
		this.vinculo = vinculo;
		this.sexo = sexo;
		this.idade = idade;
		this.contatoEmergencia = contatoEmergencia;
		this.endereco = endereco;
		this.lembrancas = lembrancas;
		this.diagnosticado = diagnosticado;
		this.fotografia = fotografia;
	}

	public Vinculo() {
		super();
		// TODO Auto-generated constructor stub
	}
}

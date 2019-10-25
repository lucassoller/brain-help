package com.example.demo.model;

import java.util.List;
import java.util.Optional;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Medico extends Usuario{

	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = true)
	private String sobrenome;
	
	@Id
	@Column(nullable = false, unique = true)
	private String email;
	
	private String senha;
	
	@Column(nullable = false)
	private boolean google;
	
	@OneToOne
	@JoinColumn(name="codEndereco")
	private Endereco endereco;
	
	@Column(nullable = false)
	private String especializacao;
	
	@OneToMany(mappedBy = "medico")
	private List<Diagnosticado> diagnosticados;
	
    @JsonIgnore
    public Optional<String> getRole() {
        return Optional.of("Usuario normal");
    }

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Diagnosticado> getDiagnosticados() {
		return diagnosticados;
	}

	public void setDiagnosticados(List<Diagnosticado> diagnosticados) {
		this.diagnosticados = diagnosticados;
	}
	
	public boolean isGoogle() {
		return google;
	}

	public void setGoogle(boolean google) {
		this.google = google;
	}
	

	public String getSobrenome() {
		return sobrenome;
	}


	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}


	public Endereco getEndereco() {
		return endereco;
	}


	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}


	public String getEspecializacao() {
		return especializacao;
	}


	public void setEspecializacao(String especializacao) {
		this.especializacao = especializacao;
	}

	public Medico(String email, String senha, String nome, String sobrenome,
			boolean google, Endereco endereco, String especializacao, List<Diagnosticado> diagnosticados) {
		super(email, senha);
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.email = email;
		this.senha = senha;
		this.google = google;
		this.endereco = endereco;
		this.especializacao = especializacao;
		this.diagnosticados = diagnosticados;
	}


	public Medico() {
		super();
		// TODO Auto-generated constructor stub
	}
}

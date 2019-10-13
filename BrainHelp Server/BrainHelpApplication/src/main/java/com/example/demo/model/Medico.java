package com.example.demo.model;

import java.util.List;
import java.util.Optional;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Medico extends Usuario{
	
	@Id
	private String codMedico;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private String sobrenome;
	
	@Column(nullable = false, unique = true)
	private String email;
	
	@Column(nullable = false)
	private String senha;
	
	@Column(nullable = false)
	private String localTrabalho;
	
	@Column(nullable = false)
	private String especializacao;
	
	@OneToMany(mappedBy = "medico")
	private List<Diagnosticado> diagnosticados;
	
    @JsonIgnore
    public Optional<String> getRole() {
        return Optional.of("Usuario normal");
    }

	public String getCodMedico() {
		return codMedico;
	}

	public void setCodMedico(String codMedico) {
		this.codMedico = codMedico;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
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

	public String getLocalTrabalho() {
		return localTrabalho;
	}

	public void setLocalTrabalho(String localTrabalho) {
		this.localTrabalho = localTrabalho;
	}

	public String getEspecializacao() {
		return especializacao;
	}

	public void setEspecializacao(String especializacao) {
		this.especializacao = especializacao;
	}

	public List<Diagnosticado> getDiagnosticados() {
		return diagnosticados;
	}

	public void setDiagnosticados(List<Diagnosticado> diagnosticados) {
		this.diagnosticados = diagnosticados;
	}

	public Medico(String codMedico, String nome, String sobrenome, String email, String senha, String localTrabalho,
			String especializacao, List<Diagnosticado> diagnosticados) {
		super();
		this.codMedico = codMedico;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.email = email;
		this.senha = senha;
		this.localTrabalho = localTrabalho;
		this.especializacao = especializacao;
		this.diagnosticados = diagnosticados;
	}

	public Medico() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}

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
	
	@Column(nullable = false)
	private String sobrenome;
	
	@Id
	@Column(nullable = false, unique = true)
	private String email;
	
	private String senha;
	
	@Column(nullable = false)
	private boolean google;
	
	@Column(nullable = false)
	private String telefone;
	
	private String foto;
	
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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Medico(String email, String senha, String nome, String sobrenome,
			boolean google, String telefone, Endereco endereco, String especializacao,
			List<Diagnosticado> diagnosticados, String foto) {
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.email = email;
		this.senha = senha;
		this.google = google;
		this.telefone = telefone;
		this.endereco = endereco;
		this.especializacao = especializacao;
		this.diagnosticados = diagnosticados;
		this.foto = foto;
	}

	public Medico() {
		super();
		// TODO Auto-generated constructor stub
	}
}

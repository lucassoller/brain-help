package com.example.alunoinfo.retrofitexemplo;

public class Medico{

	
	private String nome;
	
	private String sobrenome;
	
	private String email;
	
	private String senha;
	
	private boolean google;
	
	private String telefone;

	private String especializacao;


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

	public Medico(String nome, String sobrenome, String email, String senha, boolean google, String telefone, String especializacao) {
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.email = email;
		this.senha = senha;
		this.google = google;
		this.telefone = telefone;
		this.especializacao = especializacao;
	}

	public Medico() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Medico{" +
				"nome='" + nome + '\'' +
				", sobrenome='" + sobrenome + '\'' +
				", email='" + email + '\'' +
				", senha='" + senha + '\'' +
				", google=" + google +
				", telefone='" + telefone + '\'' +
				", especializacao='" + especializacao + '\'' +
				'}';
	}
}

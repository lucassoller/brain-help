package com.example.demo.model;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Usuario {
	private String email;
	private String senha;
	@JsonIgnore
    public Optional<String> getRole() {
        return Optional.of("Usuario normal");
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
	public Usuario(String email, String senha) {
		super();
		this.email = email;
		this.senha = senha;
	}
	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}

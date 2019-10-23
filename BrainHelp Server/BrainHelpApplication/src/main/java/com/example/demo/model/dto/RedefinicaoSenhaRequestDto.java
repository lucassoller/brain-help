package com.example.demo.model.dto;

public class RedefinicaoSenhaRequestDto {
	private String email;
	private String senha;
	
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
	
	public RedefinicaoSenhaRequestDto(String email, String senha) {
		super();
		this.email = email;
		this.senha = senha;
	}
	
	public RedefinicaoSenhaRequestDto() {
		super();
		// TODO Auto-generated constructor stub
	}		
}

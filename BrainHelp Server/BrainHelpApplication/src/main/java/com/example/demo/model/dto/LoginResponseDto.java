package com.example.demo.model.dto;

public class LoginResponseDto {
	private String email;
	private String token;
	private boolean google;
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
	
	public boolean isGoogle() {
		return google;
	}

	public void setGoogle(boolean google) {
		this.google = google;
	}
	
	public LoginResponseDto(String email, String token, boolean google) {
		super();
		this.email = email;
		this.token = token;
		this.google = google;
	}

	public LoginResponseDto() {
		super();
		// TODO Auto-generated constructor stub
	}
}
package com.brain.help.dto.login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDTO {

	private String token;
	private Long id;
	private String nome;
	private String email;
	private String role; // "ROLE_MEDICO" ou "ROLE_DEPENDENTE"
}
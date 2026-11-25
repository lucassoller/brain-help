package com.brain.help.controller;

import com.brain.help.dto.login.LoginRequestDTO;
import com.brain.help.dto.login.LoginResponseDTO;
import com.brain.help.dto.senha.RecuperarSenhaDTO;
import com.brain.help.dto.senha.RedefinirSenhaDTO;
import com.brain.help.service.LoginService;
import com.brain.help.service.RecuperacaoSenhaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/public")
@RequiredArgsConstructor
public class AuthController {

	private final LoginService loginService;
	private final RecuperacaoSenhaService recuperacaoSenhaService;

	@PostMapping("/login/medico")
	public ResponseEntity<LoginResponseDTO> loginMedico(@Valid @RequestBody LoginRequestDTO dto) {
		return ResponseEntity.ok(loginService.loginMedico(dto));
	}

	@PostMapping("/login/dependente")
	public ResponseEntity<LoginResponseDTO> loginDependente(@Valid @RequestBody LoginRequestDTO dto) {
		return ResponseEntity.ok(loginService.loginDependente(dto));
	}

	@PostMapping("/recuperar-senha/medico")
	public ResponseEntity<Void> recuperarSenhaMedico(@Valid @RequestBody RecuperarSenhaDTO dto) throws IOException {
		recuperacaoSenhaService.solicitarRecuperacaoMedico(dto);
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/recuperar-senha/dependente")
	public ResponseEntity<Void> recuperarSenhaDependente(@Valid @RequestBody RecuperarSenhaDTO dto) throws IOException {
		recuperacaoSenhaService.solicitarRecuperacaoDependente(dto);
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/redefinir-senha")
	public ResponseEntity<Void> redefinirSenha(@Valid @RequestBody RedefinirSenhaDTO dto) {
		recuperacaoSenhaService.redefinirSenha(dto);
		return ResponseEntity.noContent().build();
	}
}
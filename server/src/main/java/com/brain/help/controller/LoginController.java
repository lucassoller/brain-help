package com.brain.help.controller;

import com.brain.help.dto.login.LoginRequestDTO;
import com.brain.help.dto.login.LoginResponseDTO;
import com.brain.help.service.login.LoginService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public/login")
@RequiredArgsConstructor
public class LoginController {

	private final LoginService loginService;

	@PostMapping("/medico")
	public ResponseEntity<LoginResponseDTO> loginMedico(@Valid @RequestBody LoginRequestDTO dto) {
		return ResponseEntity.ok(loginService.loginMedico(dto));
	}

	@PostMapping("/dependente")
	public ResponseEntity<LoginResponseDTO> loginDependente(@Valid @RequestBody LoginRequestDTO dto) {
		return ResponseEntity.ok(loginService.loginDependente(dto));
	}
}
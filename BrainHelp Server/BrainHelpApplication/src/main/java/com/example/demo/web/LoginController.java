package com.example.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.dto.LoginRequestDto;
import com.example.demo.model.dto.LoginResponseDto;
import com.example.demo.service.diagnosticado.LogarDiagnosticadoService;
import com.example.demo.service.medico.LogarMedicoService;

@RestController
@RequestMapping("/public/login")
public class LoginController {

	@Autowired
	LogarMedicoService logarMedico;
	
	@Autowired
	LogarDiagnosticadoService logarDiagnosticado;
	
	@PostMapping("/medico")
	public LoginResponseDto logarMedico(@RequestBody LoginRequestDto loginDto) {
		return logarMedico.logar(loginDto);
	}
	
	@PostMapping("/diagnosticado")
	public LoginResponseDto logarDiagnosticado(@RequestBody LoginRequestDto loginDto) {
		return logarDiagnosticado.logar(loginDto);
	}
}

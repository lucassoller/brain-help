package com.brain.help.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.brain.help.entity.Medico;
import com.brain.help.dto.login.LoginRequestDto;
import com.brain.help.dto.login.LoginResponseDto;
import com.brain.help.repository.DiagnosticadoRepository;
import com.brain.help.repository.MedicoRepository;
import com.brain.help.service.dependente.LogarDiagnosticadoService;
import com.brain.help.service.medico.LogarMedicoGoogleService;
import com.brain.help.service.medico.LogarMedicoService;

@RestController
@RequestMapping("/public/login")
public class LoginController {

	@Autowired
	LogarMedicoService logarMedico;
	
	@Autowired
	LogarMedicoGoogleService logarMedicoNoGoogle;
	
	@Autowired
	LogarDiagnosticadoService logarDiagnosticado;
	
	@Autowired
	DiagnosticadoRepository d;
	
	@Autowired
	MedicoRepository mr;
	
	@PostMapping("/medico")
	public LoginResponseDto logarMedico(@RequestBody LoginRequestDto loginDto) {
		return logarMedico.logar(loginDto);
	}
	
	@PostMapping("/medico/google")
	public LoginResponseDto logarMedicoGoogle(@RequestBody LoginRequestDto loginDto) {
		return logarMedicoNoGoogle.logar(loginDto);
	}
	
	@PostMapping("/diagnosticado")
	public LoginResponseDto logarDiagnosticado(@RequestBody LoginRequestDto loginDto) {
		return logarDiagnosticado.logar(loginDto);
	}	
	
	@GetMapping("/todos")
	public List<Medico> todos() {
		return mr.findAll();
	}
}

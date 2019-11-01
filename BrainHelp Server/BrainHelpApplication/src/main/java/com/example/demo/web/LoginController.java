package com.example.demo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Diagnosticado;
import com.example.demo.model.Medico;
import com.example.demo.model.dto.LoginRequestDto;
import com.example.demo.model.dto.LoginResponseDto;
import com.example.demo.repository.DiagnosticadoRepository;
import com.example.demo.repository.MedicoRepository;
import com.example.demo.service.diagnosticado.LogarDiagnosticadoService;
import com.example.demo.service.medico.LogarMedicoGoogleService;
import com.example.demo.service.medico.LogarMedicoService;

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

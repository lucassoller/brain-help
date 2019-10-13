package com.example.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.Diagnosticado;
import com.example.demo.model.Medico;
import com.example.demo.repository.MedicoRepository;
import com.example.demo.service.diagnosticado.CadastrarDiagnosticadoService;
import com.example.demo.service.medico.CadastrarMedicoService;

@RestController
@RequestMapping("/public/registro")
public class RegistroController {

	@Autowired
	CadastrarMedicoService cadastrarMedico;
	
	@Autowired
	CadastrarDiagnosticadoService cadastrarDiagnosticado;
	
	@Autowired
	MedicoRepository medicoRepository;
	
	@PostMapping("/medico")
	public void cadastrarMedico(@RequestBody Medico medico) {
		cadastrarMedico.salvar(medico);
	}
	
	@PostMapping("/diagnosticado")
	public void cadastrarDiagnosticado(@RequestBody Diagnosticado diagnosticado) {
		cadastrarDiagnosticado.salvar(diagnosticado);
	}
}

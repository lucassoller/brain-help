package com.brain.help.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.brain.help.entity.Dependente;
import com.brain.help.entity.Medico;
import com.brain.help.repository.MedicoRepository;
import com.brain.help.repository.TesteRepository;
import com.brain.help.service.medico.CadastrarMedicoService;

@RestController
@RequestMapping("/public/registro")
public class RegistroController {

	@Autowired
	CadastrarMedicoService cadastrarMedico;
	
	@Autowired
	CadastrarDiagnosticadoService cadastrarDiagnosticado;
	
	@Autowired
	EditarSenhaMedicoService editarSenhaMedico;
	
	@Autowired
	MedicoRepository medicoRepository;
	
	@Autowired
	TesteRepository testeRepository;
	
	@GetMapping("/medico/teste")
	public List<String> teste() throws Exception {
		List<String> testes = new ArrayList<>();
		for(Atividade t : testeRepository.findAll()) {
			testes.add(t.getDataRealizacao());
		}
		return testes;
	}
	
	@PostMapping("/medico/teste2")
	public void teste2() throws Exception {
		Atividade atividade = new Atividade();
		atividade.setDataRealizacao(new Date()+"dataaa");
		testeRepository.save(atividade);
	}	
	
	
	@PostMapping("/medico")
	public void cadastrarMedico(@RequestBody Medico medico) throws Exception {
		cadastrarMedico.salvar(medico);
	}
	
	@PutMapping("/medico/editar/senha")
	public void editarMedico(@RequestBody RedefinicaoSenhaRequestDto redefinicao) {
		editarSenhaMedico.editar(redefinicao);
	}
	
	@PostMapping("/diagnosticado")
	public void cadastrarDiagnosticado(@RequestBody Dependente dependente) throws Exception {
		cadastrarDiagnosticado.salvar(dependente);
	}
}

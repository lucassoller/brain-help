package com.example.demo.web;

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
import com.example.demo.model.Diagnosticado;
import com.example.demo.model.Medico;
import com.example.demo.model.Teste;
import com.example.demo.model.dto.RedefinicaoSenhaRequestDto;
import com.example.demo.repository.MedicoRepository;
import com.example.demo.repository.TesteRepository;
import com.example.demo.service.diagnosticado.CadastrarDiagnosticadoService;
import com.example.demo.service.medico.CadastrarMedicoService;
import com.example.demo.service.medico.EditarSenhaMedicoService;

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
		for(Teste t : testeRepository.findAll()) {
			testes.add(t.getDataRealizacao());
		}
		return testes;
	}
	
	@PostMapping("/medico/teste2")
	public void teste2() throws Exception {
		Teste teste = new Teste();
		teste.setDataRealizacao(new Date()+"dataaa");
		testeRepository.save(teste);
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
	public void cadastrarDiagnosticado(@RequestBody Diagnosticado diagnosticado) throws Exception {
		cadastrarDiagnosticado.salvar(diagnosticado);
	}
}

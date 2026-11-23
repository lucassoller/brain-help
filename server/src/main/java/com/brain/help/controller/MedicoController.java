package com.brain.help.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.brain.help.entity.Medico;
import com.brain.help.dto.senha.RedefinicaoSenhaRequestDto;
import com.brain.help.repository.MedicoRepository;
import com.brain.help.service.medico.BuscarMedicoPorEmailService;
import com.brain.help.service.medico.DeletarMedicoService;
import com.brain.help.service.medico.EditarMedicoService;
import com.brain.help.service.medico.EditarSenhaMedicoService;
import com.brain.help.service.medico.VincularDiagnosticadoService;

@RestController
@RequestMapping("/medico")
public class MedicoController {
	
	@Autowired
	private MedicoRepository medicoRepository;
	
	@Autowired
	private VincularDiagnosticadoService vincularDiagnosticado;
	
	@Autowired
	private BuscarMedicoPorEmailService buscarMedicoPorEmail;
	
	@Autowired
	private EditarMedicoService editarMedico;
	
	@Autowired
	EditarSenhaMedicoService editarSenhaMedico;
	
	@Autowired
	private DeletarMedicoService deletarMedico;
	
	@GetMapping("/buscar/todos")
	public List<Medico> buscarTodos(){
		return medicoRepository.findAll();
	}
	
	@GetMapping("/buscar/{EMAIL}")
	public Medico buscarPorEmail(@PathVariable("EMAIL") String email){
		return buscarMedicoPorEmail.buscar(email, true);
	}
	
	@GetMapping("/vincular/{EMAIL}")
	public void vincularDiagnosticado(@AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable("EMAIL") String email){
		vincularDiagnosticado.vincular(userPrincipal.getEmail(), email);
	}
	
	@GetMapping("/teste")
	public List<Medico> medico (@AuthenticationPrincipal UserPrincipal userPrincipal){
		return medicoRepository.findAll();
	}
	
	@PutMapping("/editar")
	public void cadastrarMedico(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody Medico medico) throws Exception {
		editarMedico.editar(userPrincipal.getEmail(), medico);
	}
	
	@PutMapping("/editar/senha")
	public void editarMedico(@RequestBody RedefinicaoSenhaRequestDto redefinicao) {
		editarSenhaMedico.editar(redefinicao);
	}
	
	@DeleteMapping("/deletar")
	public void deletarMedicamento(@AuthenticationPrincipal UserPrincipal userPrincipal) {
		deletarMedico.deletar(userPrincipal.getEmail());
	}
}
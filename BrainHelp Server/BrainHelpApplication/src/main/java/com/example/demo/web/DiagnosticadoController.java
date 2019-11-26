package com.example.demo.web;

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
import com.example.demo.model.Diagnosticado;
import com.example.demo.model.security.UserPrincipal;
import com.example.demo.repository.DiagnosticadoRepository;
import com.example.demo.service.diagnosticado.BuscarDiagnosticadoPorEmailService;
import com.example.demo.service.diagnosticado.BuscarDiagnosticadosPorNomeOuEmailService;
import com.example.demo.service.diagnosticado.BuscarDiagnosticadosVinculadosService;
import com.example.demo.service.diagnosticado.DeletarDiagnosticadoService;
import com.example.demo.service.diagnosticado.EditarDiagnosticadoService;

@RestController
@RequestMapping("/diagnosticado")
public class DiagnosticadoController {
	
	@Autowired
	private DiagnosticadoRepository diagnosticadoRepository;
	
	@Autowired
	private BuscarDiagnosticadoPorEmailService buscarDiagnosticadoPorEmail;
	
	@Autowired
	private BuscarDiagnosticadosPorNomeOuEmailService buscarDiagnosticados;
	
	@Autowired
	private BuscarDiagnosticadosVinculadosService buscarDiagnosticadosVinculados;
	
	@Autowired
	private EditarDiagnosticadoService editarDiagnosticado;
	
	@Autowired
	private DeletarDiagnosticadoService deletarDiagnosticado;
	
	@GetMapping("/buscar/todos")
	public List<Diagnosticado> buscarTodos(){
		return diagnosticadoRepository.findAll();
	}
	
	@GetMapping("/buscar/{EMAIL}")
	public Diagnosticado buscarPorEmail(@PathVariable("EMAIL") String email){
		return buscarDiagnosticadoPorEmail.buscar(email);
	}
	
	@GetMapping("/buscar/logado")
	public Diagnosticado buscarLogado(@AuthenticationPrincipal UserPrincipal userPrincipal){
		return buscarDiagnosticadoPorEmail.buscar(userPrincipal.getEmail());
	}
	
	@GetMapping("/buscar/todos/vinculados/{NOME}")
	public List<Diagnosticado> buscarTodosVinculadosPorNome(@AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable("NOME") String nome){
		return buscarDiagnosticados.buscar(userPrincipal.getEmail(), nome, true);
	}
	
	@GetMapping("/buscar/todos/vinculados")
	public List<Diagnosticado> buscarTodosVinculados(@AuthenticationPrincipal UserPrincipal userPrincipal){
		return buscarDiagnosticadosVinculados.buscar(userPrincipal.getEmail());
	}
	
	@GetMapping("/buscar/todos/desvinculados/{NOME}")
	public List<Diagnosticado> buscarTodosNaoVinculados(@AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable("NOME") String nome){
		return buscarDiagnosticados.buscar(userPrincipal.getEmail(), nome, false);
	}
	
	@PutMapping("/editar")
	public void editarDiagnosticado(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody Diagnosticado diagnosticado) {
		editarDiagnosticado.editar(userPrincipal.getEmail(), diagnosticado);
	}
	
	@DeleteMapping("/deletar")
	public void deletarDiagnosticado(@AuthenticationPrincipal UserPrincipal userPrincipal){
		deletarDiagnosticado.deletar(userPrincipal.getEmail());
	}
}
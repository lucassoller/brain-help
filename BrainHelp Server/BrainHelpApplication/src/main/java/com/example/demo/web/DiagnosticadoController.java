package com.example.demo.web;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.Diagnosticado;
import com.example.demo.repository.DiagnosticadoRepository;
import com.example.demo.service.diagnosticado.BuscarDiagnosticadoPorEmailService;
import com.example.demo.service.diagnosticado.BuscarDiagnosticadosPorEmailService;
import com.example.demo.service.diagnosticado.BuscarDiagnosticadosPorNomeService;

@RestController
@RequestMapping("/diagnosticado")
public class DiagnosticadoController {
	
	@Autowired
	private DiagnosticadoRepository diagnosticadoRepository;
	
	@Autowired
	private BuscarDiagnosticadoPorEmailService buscarDiagnosticadoPorEmail;
	
	@Autowired
	private BuscarDiagnosticadosPorEmailService buscarDiagnosticadosPorEmail;
	
	@Autowired
	private BuscarDiagnosticadosPorNomeService buscarDiagnosticadosPorNome;
	
	@GetMapping("/buscar/todos")
	public List<Diagnosticado> buscarTodos(){
		return diagnosticadoRepository.findAll();
	}
	
	@GetMapping("/buscar/{EMAIL}")
	public Diagnosticado buscarPorEmail(@PathVariable("EMAIL") String email){
		return buscarDiagnosticadoPorEmail.buscar(email);
	}
	
	@GetMapping("/buscar/todos/nome/{NOME}")
	public List<Diagnosticado> buscarTodosPorNome(@PathVariable("NOME") String nome){
		return buscarDiagnosticadosPorNome.buscar(nome);
	}
	
	@GetMapping("/buscar/todos/email/{EMAIL}")
	public List<Diagnosticado> buscarTodosPorEmail(@PathVariable("EMAIL") String email){
		return buscarDiagnosticadosPorEmail.buscar(email);
	}
}

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

@RestController
@RequestMapping("/diagnosticado")
public class DiagnosticadoController {
	
	@Autowired
	private DiagnosticadoRepository diagnosticadoRepository;
	
	@Autowired
	private BuscarDiagnosticadoPorEmailService buscarDiagnosticadoPorEmail;
	
	@GetMapping("/buscar/todos")
	public List<Diagnosticado> buscarTodos(){
		return diagnosticadoRepository.findAll();
	}
	
	@GetMapping("/buscar/{EMAIL}")
	public Diagnosticado buscarPorEmail(@PathVariable("EMAIL") String email){
		return buscarDiagnosticadoPorEmail.buscar(email);
	}
}

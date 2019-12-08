package com.example.demo.service.diagnosticado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Diagnosticado;
import com.example.demo.repository.DiagnosticadoRepository;

@Service
public class DeletarDiagnosticadoService {

	@Autowired
	BuscarDiagnosticadoPorEmailService buscarDiagnosticado;
	
	@Autowired
	DiagnosticadoRepository diagnosticadoRepository;
	
	public void deletar(String emailDiagnosticado) {
		Diagnosticado diagnosticado = buscarDiagnosticado.buscar(emailDiagnosticado, false);
		diagnosticadoRepository.delete(diagnosticado);
	}
}

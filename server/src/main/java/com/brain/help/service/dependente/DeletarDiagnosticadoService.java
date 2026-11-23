package com.brain.help.service.dependente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.brain.help.entity.Dependente;
import com.brain.help.repository.DiagnosticadoRepository;

@Service
public class DeletarDiagnosticadoService {

	@Autowired
	BuscarDiagnosticadoPorEmailService buscarDiagnosticado;
	
	@Autowired
	DiagnosticadoRepository diagnosticadoRepository;
	
	public void deletar(String emailDiagnosticado) {
		Dependente dependente = buscarDiagnosticado.buscar(emailDiagnosticado, false);
		diagnosticadoRepository.delete(dependente);
	}
}

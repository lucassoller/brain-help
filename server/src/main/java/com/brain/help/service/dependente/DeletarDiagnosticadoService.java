package com.brain.help.service.dependente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.brain.help.entity.Dependente;
import com.brain.help.repository.DependenteRepository;

@Service
public class DeletarDiagnosticadoService {

	@Autowired
	BuscarDiagnosticadoPorEmailService buscarDiagnosticado;
	
	@Autowired
	DependenteRepository dependenteRepository;
	
	public void deletar(String emailDiagnosticado) {
		Dependente dependente = buscarDiagnosticado.buscar(emailDiagnosticado, false);
		dependenteRepository.delete(dependente);
	}
}

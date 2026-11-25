package com.brain.help.service.desempenho;

import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.brain.help.entity.Desempenho;
import com.brain.help.entity.Dependente;
import com.brain.help.repository.DesempenhoRepository;

@Service
public class BuscarDesempenhosDoUsuarioService {

	@Autowired
	DesempenhoRepository desempenhoRepository;
	
	@Autowired
	BuscarDiagnosticadoPorEmailService buscarDiagnosticado;

	public List<Desempenho> buscar(String emailDiagnosticado) {
		if (Objects.isNull(emailDiagnosticado) || emailDiagnosticado.isEmpty()) {
			throw new IllegalArgumentException("O email do diagnosticado não pode estar em branco");
		}
		Dependente dependente = buscarDiagnosticado.buscar(emailDiagnosticado, false);
		return desempenhoRepository.findByDiagnosticado(dependente);
	}
}
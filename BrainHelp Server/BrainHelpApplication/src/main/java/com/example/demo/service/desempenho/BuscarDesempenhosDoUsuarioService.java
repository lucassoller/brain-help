package com.example.demo.service.desempenho;

import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Desempenho;
import com.example.demo.model.Diagnosticado;
import com.example.demo.repository.DesempenhoRepository;
import com.example.demo.service.diagnosticado.BuscarDiagnosticadoPorEmailService;

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
		Diagnosticado diagnosticado = buscarDiagnosticado.buscar(emailDiagnosticado);
		return desempenhoRepository.findByDiagnosticado(diagnosticado);
	}
}
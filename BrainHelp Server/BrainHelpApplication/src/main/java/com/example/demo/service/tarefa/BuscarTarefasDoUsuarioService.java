package com.example.demo.service.tarefa;

import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Diagnosticado;
import com.example.demo.model.Tarefa;
import com.example.demo.repository.TarefaRepository;
import com.example.demo.service.diagnosticado.BuscarDiagnosticadoPorEmailService;

@Service
public class BuscarTarefasDoUsuarioService {

	@Autowired
	TarefaRepository tarefaRepository;
	
	@Autowired
	BuscarDiagnosticadoPorEmailService buscarDiagnosticado;

	public List<Tarefa> buscar(String emailDiagnosticado) {
		if (Objects.isNull(emailDiagnosticado) || emailDiagnosticado.isEmpty()) {
			throw new IllegalArgumentException("O email do diagnosticado não pode estar em branco");
		}
		Diagnosticado diagnosticado = buscarDiagnosticado.buscar(emailDiagnosticado);
		return tarefaRepository.findByDiagnosticado(diagnosticado);
	}
}
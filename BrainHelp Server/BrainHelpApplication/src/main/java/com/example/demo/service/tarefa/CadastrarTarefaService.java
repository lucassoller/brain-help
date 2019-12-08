package com.example.demo.service.tarefa;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Diagnosticado;
import com.example.demo.model.Tarefa;
import com.example.demo.model.enumerated.StatusTarefa;
import com.example.demo.repository.TarefaRepository;
import com.example.demo.service.diagnosticado.BuscarDiagnosticadoPorEmailService;

@Service
public class CadastrarTarefaService {
	
	@Autowired
	TarefaRepository tarefaRepository;	
	
	@Autowired
	BuscarDiagnosticadoPorEmailService buscarDiagnosticado;
	
	public void salvar(String emailDiagnosticado, Tarefa tarefa) {
		if (Objects.isNull(tarefa.getTarefa()) || tarefa.getTarefa().isEmpty()) {
			throw new IllegalArgumentException("A tarefa não pode estar em branco");
		}
		
		if (Objects.isNull(tarefa.getDescricao()) || tarefa.getDescricao().isEmpty()) {
			throw new IllegalArgumentException("A descrição da tarefa não pode estar em branco");
		}
		
		if (Objects.isNull(tarefa.getDataRealizacao())) {
			throw new IllegalArgumentException("A data da realização da tarefa não pode estar em branco");
		}
		
		Diagnosticado diagnosticado = buscarDiagnosticado.buscar(emailDiagnosticado, false);
				
		tarefa.setStatusTarefa(StatusTarefa.NAO_CONCLUIDA);
		tarefa.setDiagnosticado(diagnosticado);
		tarefaRepository.save(tarefa);
	}
}
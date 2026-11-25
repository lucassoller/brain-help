package com.brain.help.service.tarefa;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.brain.help.entity.Dependente;
import com.brain.help.entity.Tarefa;
import com.brain.help.enums.StatusTarefa;
import com.brain.help.repository.TarefaRepository;

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
		
		Dependente dependente = buscarDiagnosticado.buscar(emailDiagnosticado, false);
				
		tarefa.setStatusTarefa(StatusTarefa.NAO_CONCLUIDA);
		tarefa.setDependente(dependente);
		tarefaRepository.save(tarefa);
	}
}
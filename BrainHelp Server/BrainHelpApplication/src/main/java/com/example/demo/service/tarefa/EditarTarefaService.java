package com.example.demo.service.tarefa;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Tarefa;
import com.example.demo.repository.TarefaRepository;

@Service
public class EditarTarefaService {
	
	@Autowired
	BuscarTarefaPorIdService buscarTarefa;
	
	@Autowired
	TarefaRepository tarefaRepository;
	
	public void editar(Tarefa tarefa) {
		Tarefa tarefaParaEditar = buscarTarefa.buscar(tarefa.getCodTarefa());
		
		if (!Objects.isNull(tarefa.getTarefa()) && !tarefa.getTarefa().isEmpty()) {
			tarefaParaEditar.setTarefa(tarefa.getTarefa());
		}
		
		if (!Objects.isNull(tarefa.getDescricao()) && !tarefa.getDescricao().isEmpty()) {
			tarefaParaEditar.setDescricao(tarefa.getDescricao());
		}
		
		if (!Objects.isNull(tarefa.getDataRealizacao())) {
			tarefaParaEditar.setDataRealizacao(tarefa.getDataRealizacao());
		}
		
		if (!Objects.isNull(tarefa.getStatusTarefa())) {
			tarefaParaEditar.setStatusTarefa(tarefa.getStatusTarefa());
		}
		
		tarefaRepository.save(tarefaParaEditar);
	}
}
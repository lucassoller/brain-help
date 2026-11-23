package com.brain.help.service.tarefa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.brain.help.entity.Tarefa;
import com.brain.help.repository.TarefaRepository;

@Service
public class DeletarTarefaService {

	@Autowired
	BuscarTarefaPorIdService buscarTarefa;
	
	@Autowired
	TarefaRepository tarefaRepository;
	
	public void deletar(Integer codTarefa) {
		Tarefa tarefa = buscarTarefa.buscar(codTarefa);
		tarefaRepository.delete(tarefa);
	}
}

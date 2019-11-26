package com.example.demo.service.tarefa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Tarefa;
import com.example.demo.repository.TarefaRepository;

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

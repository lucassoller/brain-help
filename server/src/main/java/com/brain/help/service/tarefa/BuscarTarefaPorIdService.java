package com.brain.help.service.tarefa;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.brain.help.entity.Tarefa;
import com.brain.help.repository.TarefaRepository;

@Service
public class BuscarTarefaPorIdService {

	@Autowired
	TarefaRepository tarefaRepository;

	public Tarefa buscar(Integer codTarefa) {
		if ((Objects.isNull(codTarefa))) {
			throw new IllegalArgumentException("O código da tarefa não pode estar em branco");
		}
		
		return tarefaRepository.findById(codTarefa)
				.orElseThrow(() -> new IllegalArgumentException("Nenhuma tarefa foi encontrada"));
	}
}
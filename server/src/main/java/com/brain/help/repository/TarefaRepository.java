package com.brain.help.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.brain.help.entity.Dependente;
import com.brain.help.entity.Tarefa;
import com.brain.help.enums.StatusTarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Integer>{
	List<Tarefa> findByDiagnosticado(Dependente dependente);
	List<Tarefa> findByDiagnosticadoAndStatusTarefa(Dependente dependente, StatusTarefa statusTarefa);
}

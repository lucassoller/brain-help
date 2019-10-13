package com.example.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.Diagnosticado;
import com.example.demo.model.Tarefa;
import com.example.demo.model.enumerated.StatusTarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Integer>{
	List<Tarefa> findByDiagnosticado(Diagnosticado diagnosticado);
	List<Tarefa> findByDiagnosticadoAndStatusTarefa(Diagnosticado diagnosticado, StatusTarefa statusTarefa);	
}

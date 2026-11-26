package com.brain.help.repository;

import com.brain.help.entity.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

	List<Tarefa> findByDependenteIdOrderByDataCriacaoDesc(Long dependenteId);
}
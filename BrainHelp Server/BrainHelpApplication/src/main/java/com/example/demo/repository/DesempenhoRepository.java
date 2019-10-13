package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Atividade;
import com.example.demo.model.Desempenho;
import com.example.demo.model.Diagnosticado;
import com.example.demo.model.enumerated.AvaliacaoDesempenho;

public interface DesempenhoRepository extends JpaRepository<Desempenho, Integer>{
	List<Desempenho> findByDiagnosticado(Diagnosticado diagnosticado);	
	List<Desempenho> findByDiagnosticadoAndAtividade(Diagnosticado diagnosticado, Atividade atividade);	
	List<Desempenho> findByDiagnosticadoAndAtividadeAndAvaliacaoDesempenho(Diagnosticado diagnosticado, Atividade atividade, AvaliacaoDesempenho avaliacaoDesempenho);	
	List<Desempenho> findByDiagnosticadoAndAvaliacaoDesempenho(Diagnosticado diagnosticado, AvaliacaoDesempenho avaliacaoDesempenho);	
}
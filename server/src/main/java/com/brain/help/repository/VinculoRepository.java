package com.brain.help.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.brain.help.entity.Dependente;
import com.brain.help.entity.Vinculo;

public interface VinculoRepository extends JpaRepository<Vinculo, Integer>{
	List<Vinculo> findByDiagnosticado(Dependente dependente);
	List<Vinculo> findByDiagnosticadoAndVinculo(Dependente dependente, String vinculo);
	List<Vinculo> findByDiagnosticadoAndNomeContaining(Dependente dependente, String nome);
}

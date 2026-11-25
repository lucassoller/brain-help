package com.brain.help.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.brain.help.entity.Desempenho;
import com.brain.help.entity.Dependente;

public interface DesempenhoRepository extends JpaRepository<Desempenho, Long>{
	List<Desempenho> findByDiagnosticadoAndDataRealizacaoBetween(Dependente dependente, Date dataInicial, Date dataFinal);

	List<Desempenho> findByDependenteIdOrderByDataRealizacaoDesc(Long dependenteId);
}
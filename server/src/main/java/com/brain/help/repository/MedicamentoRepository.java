package com.brain.help.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.brain.help.entity.Dependente;
import com.brain.help.entity.Medicamento;

public interface MedicamentoRepository extends JpaRepository<Medicamento, Integer>{
	List<Medicamento> findByDiagnosticado(Dependente dependente);
	List<Medicamento> findByDiagnosticadoAndFuncao(Dependente dependente, String funcao);
}

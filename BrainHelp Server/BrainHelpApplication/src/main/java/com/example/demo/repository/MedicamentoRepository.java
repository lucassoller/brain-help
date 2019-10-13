package com.example.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.Diagnosticado;
import com.example.demo.model.Medicamento;

public interface MedicamentoRepository extends JpaRepository<Medicamento, Integer>{
	List<Medicamento> findByDiagnosticado(Diagnosticado diagnosticado);
	List<Medicamento> findByDiagnosticadoAndFuncao(Diagnosticado diagnosticado, String funcao);
}

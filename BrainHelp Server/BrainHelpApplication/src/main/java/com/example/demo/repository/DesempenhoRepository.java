package com.example.demo.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.Desempenho;
import com.example.demo.model.Diagnosticado;

public interface DesempenhoRepository extends JpaRepository<Desempenho, Integer>{
	List<Desempenho> findByDiagnosticadoAndDataRealizacaoBetween(Diagnosticado diagnosticado, Date dataInicial, Date dataFinal);
	List<Desempenho> findByDiagnosticado(Diagnosticado diagnosticado);

}
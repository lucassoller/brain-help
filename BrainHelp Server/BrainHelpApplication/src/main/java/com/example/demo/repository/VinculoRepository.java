package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.Diagnosticado;
import com.example.demo.model.Vinculo;

public interface VinculoRepository extends JpaRepository<Vinculo, Integer>{
	List<Vinculo> findByDiagnosticado(Diagnosticado diagnosticado);
	List<Vinculo> findByDiagnosticadoAndVinculo(Diagnosticado diagnosticado, String vinculo);
	List<Vinculo> findByDiagnosticadoAndNomeContaining(Diagnosticado diagnosticado, String nome);
	List<Vinculo> findByDiagnosticadoAndContatoEmergencia(Diagnosticado diagnosticado, boolean contatoEmergencia);

}

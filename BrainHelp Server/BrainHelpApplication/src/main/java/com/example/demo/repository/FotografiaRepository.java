package com.example.demo.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.Diagnosticado;
import com.example.demo.model.Fotografia;

public interface FotografiaRepository extends JpaRepository<Fotografia, Integer>{
	List<Fotografia> findByDiagnosticado(Diagnosticado diagnosticado);
	List<Fotografia> findByLugarContaining(String lugar);
	List<Fotografia> findByDescricaoContaining(String descricao);
	List<Fotografia> findByData(Date data);
}
package com.example.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.Diagnosticado;
import com.example.demo.model.Musica;

public interface MusicaRepository extends JpaRepository<Musica, Integer>{
	List<Musica> findByDiagnosticado(Diagnosticado diagnosticado);
	List<Musica> findByDiagnosticadoAndCantorContaining(Diagnosticado diagnosticado, String cantor);
	List<Musica> findByDiagnosticadoAndNomeContaining(Diagnosticado diagnosticado, String nome);
}

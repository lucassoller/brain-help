package com.brain.help.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.brain.help.entity.Dependente;
import com.brain.help.entity.Musica;

public interface MusicaRepository extends JpaRepository<Musica, Integer>{
	List<Musica> findByDiagnosticado(Dependente dependente);
	List<Musica> findByDiagnosticadoAndCantorContaining(Dependente dependente, String cantor);
	List<Musica> findByDiagnosticadoAndNomeContaining(Dependente dependente, String nome);
}

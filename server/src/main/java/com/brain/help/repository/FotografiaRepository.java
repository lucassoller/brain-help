package com.brain.help.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.brain.help.entity.Dependente;
import com.brain.help.entity.Fotografia;

public interface FotografiaRepository extends JpaRepository<Fotografia, Integer>{
	List<Fotografia> findByDiagnosticado(Dependente dependente);
}
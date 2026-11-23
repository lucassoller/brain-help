package com.brain.help.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.brain.help.entity.Dependente;

public interface DependenteRepository extends JpaRepository<Dependente, Integer>{
	Optional<Dependente> findByEmail(String email);
	
	@Query("SELECT d FROM Dependente d WHERE (d.medico.email LIKE ?1) AND (d.nome LIKE %?2% OR d.email LIKE %?2%) ORDER BY d.nome, d.sobrenome")
	List<Dependente> findByMedico(String email, String nome);
	
	@Query("SELECT d FROM Dependente d WHERE d.medico.email LIKE ?1 ORDER BY d.nome, d.sobrenome")
	List<Dependente> findAllByMedico(String email);
	
	@Query("SELECT d FROM Dependente d WHERE (d.medico IS NULL) AND (d.nome LIKE %?1% OR d.email LIKE %?1%) ORDER BY d.nome, d.sobrenome")
	List<Dependente> findByMedicoNotLike(String nome);
}
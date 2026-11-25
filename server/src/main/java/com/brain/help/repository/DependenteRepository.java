package com.brain.help.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.brain.help.entity.Dependente;
import org.springframework.data.repository.query.Param;

public interface DependenteRepository extends JpaRepository<Dependente, Long>{
	Optional<Dependente> findByEmail(String email);

	boolean existsByEmail(String email);

	boolean existsByEmailAndIdNot(String email, Long id);
	
	@Query("SELECT d FROM Dependente d WHERE (d.medico.email LIKE ?1) AND (d.nome LIKE %?2% OR d.email LIKE %?2%) ORDER BY d.nome, d.sobrenome")
	List<Dependente> findByMedico(String email, String nome);
	
	@Query("SELECT d FROM Dependente d WHERE d.medico.email LIKE ?1 ORDER BY d.nome, d.sobrenome")
	List<Dependente> findAllByMedico(String email);
	
	@Query("SELECT d FROM Dependente d WHERE (d.medico IS NULL) AND (d.nome LIKE %?1% OR d.email LIKE %?1%) ORDER BY d.nome, d.sobrenome")
	List<Dependente> findByMedicoNotLike(String nome);

	List<Dependente> findByMedicoIdOrderByNomeAsc(Long medicoId);

	@Query("""
        SELECT d FROM Dependente d
        WHERE d.medico IS NULL
          AND (
               LOWER(CONCAT(d.nome, ' ', d.sobrenome)) LIKE LOWER(CONCAT('%', :termo, '%'))
            OR LOWER(d.email) LIKE LOWER(CONCAT('%', :termo, '%'))
          )
        ORDER BY d.nome ASC
    """)
	List<Dependente> buscarDependentesPorNomeOuEmail(@Param("termo") String termo);
}
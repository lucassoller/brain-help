package com.brain.help.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.brain.help.entity.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long>{

	boolean existsByEmail(String email);

	boolean existsByEmailAndIdNot(String email, Long id);

	Optional<Medico> findByEmail(String email);
	Optional<Medico> findByEmailAndGoogle(String email, boolean google);
}

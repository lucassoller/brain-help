package com.brain.help.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.brain.help.entity.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Integer>{
	Optional<Medico> findByEmail(String email);
	Optional<Medico> findByEmailAndGoogle(String email, boolean google);
}

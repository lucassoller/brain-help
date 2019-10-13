package com.example.demo.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Integer>{
	Optional<Medico> findByEmail(String email);
	Optional<Medico> findByCodMedico(String codMedico);
}

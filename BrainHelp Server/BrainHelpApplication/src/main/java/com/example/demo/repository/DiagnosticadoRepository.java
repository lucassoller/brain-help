package com.example.demo.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.Diagnosticado;
import com.example.demo.model.Medico;

public interface DiagnosticadoRepository extends JpaRepository<Diagnosticado, Integer>{
	Optional<Diagnosticado> findByEmail(String email);
	List<Diagnosticado> findByMedico(Medico medico);

	List<Diagnosticado> findAllByEmailContaining(String email);
	List<Diagnosticado> findAllByNomeContaining(String nome);
}

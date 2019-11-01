package com.example.demo.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.demo.model.Diagnosticado;

public interface DiagnosticadoRepository extends JpaRepository<Diagnosticado, Integer>{
	Optional<Diagnosticado> findByEmail(String email);
	
	@Query("SELECT d FROM Diagnosticado d WHERE (d.medico.email LIKE ?1) AND (d.nome LIKE %?2% OR d.email LIKE %?2%) ORDER BY d.nome, d.sobrenome")
	List<Diagnosticado> findByMedico(String email, String nome);
	
	@Query("SELECT d FROM Diagnosticado d WHERE d.medico.email LIKE ?1 ORDER BY d.nome, d.sobrenome")
	List<Diagnosticado> findAllByMedico(String email);
	
	@Query("SELECT d FROM Diagnosticado d WHERE (d.medico = null OR d.medico.email NOT LIKE ?1) AND (d.nome LIKE %?2% OR d.email LIKE %?2%) ORDER BY d.nome, d.sobrenome")
	List<Diagnosticado> findByMedicoNotLike(String email, String nome);
}
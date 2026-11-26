package com.brain.help.repository;

import com.brain.help.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

	List<Endereco> findByDependenteIdOrderByPrincipalDescTituloAsc(Long dependenteId);

	Optional<Endereco> findByIdAndDependenteId(Long id, Long dependenteId);

	Optional<Endereco> findByDependenteIdAndPrincipalTrue(Long dependenteId);

	@Modifying
	@Query("UPDATE Endereco e SET e.principal = false WHERE e.dependente.id = :dependenteId AND e.principal = true")
	void desmarcarPrincipalDoDependente(@Param("dependenteId") Long dependenteId);
}
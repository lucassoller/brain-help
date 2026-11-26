package com.brain.help.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.brain.help.entity.Vinculo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VinculoRepository extends JpaRepository<Vinculo, Long>{

	List<Vinculo> findByDependenteIdOrderByNomeAsc(Long dependenteId);

	@Query("""
	SELECT v FROM Vinculo v
	WHERE v.dependente.id = :dependenteId
	  AND LOWER(CONCAT(v.nome, ' ', v.sobrenome)) LIKE LOWER(CONCAT('%', :nome, '%'))
	ORDER BY v.nome ASC
""")
	List<Vinculo> buscarPorDependenteENome(@Param("dependenteId") Long dependenteId,
										   @Param("nome") String nome);

	List<Vinculo> findByDependenteIdAndVinculoContainingIgnoreCaseOrderByNomeAsc(Long dependenteId, String vinculo);

	boolean existsByEnderecoId(Long enderecoId);

}

package com.brain.help.repository;

import com.brain.help.entity.Musica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;


public interface MusicaRepository extends JpaRepository<Musica, Long> {

	List<Musica> findByDependenteIdOrderByNomeMusicaAsc(Long dependenteId);

	@Query("""
        SELECT m FROM Musica m
        WHERE m.dependente.id = :dependenteId
          AND (
              LOWER(m.nomeMusica) LIKE LOWER(CONCAT('%', :termo, '%'))
              OR LOWER(m.cantor) LIKE LOWER(CONCAT('%', :termo, '%'))
          )
        ORDER BY m.nomeMusica ASC
    """)
	List<Musica> buscarPorDependenteENomeOuCantor(@Param("dependenteId") Long dependenteId,
												  @Param("termo") String termo);
}
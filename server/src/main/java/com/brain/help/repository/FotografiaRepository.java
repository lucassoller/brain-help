package com.brain.help.repository;

import com.brain.help.entity.Fotografia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FotografiaRepository extends JpaRepository<Fotografia, Long> {

	List<Fotografia> findByDependenteIdOrderByDataDesc(Long dependenteId);

	Optional<Fotografia> findByIdAndDependenteId(Long id, Long dependenteId);
}
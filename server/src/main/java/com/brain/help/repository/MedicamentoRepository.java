package com.brain.help.repository;

import com.brain.help.entity.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {

	List<Medicamento> findByDependenteIdOrderByNomeMedicamentoAsc(Long dependenteId);

	Optional<Medicamento> findByIdAndDependenteId(Long id, Long dependenteId);

}
package com.brain.help.service.medicamento;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.brain.help.entity.Medicamento;
import com.brain.help.repository.MedicamentoRepository;

@Service
public class BuscarMedicamentoPorIdService {

	@Autowired
	MedicamentoRepository medicamentoRepository;

	public Medicamento buscar(Integer codMedicamento) {
		if ((Objects.isNull(codMedicamento))) {
			throw new IllegalArgumentException("O código do medicamento não pode estar em branco");
		}
		
		return medicamentoRepository.findById(codMedicamento)
				.orElseThrow(() -> new IllegalArgumentException("Nenhum medicamento foi encontrado"));
	}
}
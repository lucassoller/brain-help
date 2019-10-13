package com.example.demo.service.medicamento;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Medicamento;
import com.example.demo.repository.MedicamentoRepository;

@Service
public class BuscarMedicamentoPorIdService {

	@Autowired
	MedicamentoRepository medicamentoRepository;

	public Medicamento buscar(Integer codMedicamento) {
		if ((Objects.isNull(codMedicamento))) {
			throw new IllegalArgumentException("O c�digo do medicamento n�o pode estar em branco");
		}
		
		return medicamentoRepository.findById(codMedicamento)
				.orElseThrow(() -> new IllegalArgumentException("Nenhum medicamento foi encontrado"));
	}
}
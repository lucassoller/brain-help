package com.brain.help.service.medicamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.brain.help.entity.Medicamento;
import com.brain.help.repository.MedicamentoRepository;

@Service
public class DeletarMedicamentoService {

	@Autowired
	BuscarMedicamentoPorIdService buscarMedicamento;
	
	@Autowired
	MedicamentoRepository medicamentoRepository;
	
	public void deletar(Integer codMedicamento) {
		Medicamento medicamento = buscarMedicamento.buscar(codMedicamento);
		medicamentoRepository.delete(medicamento);
	}
}
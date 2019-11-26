package com.example.demo.service.medicamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Medicamento;
import com.example.demo.repository.MedicamentoRepository;

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
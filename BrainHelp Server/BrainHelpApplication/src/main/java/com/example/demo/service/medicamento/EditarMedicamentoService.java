package com.example.demo.service.medicamento;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Medicamento;
import com.example.demo.repository.MedicamentoRepository;

@Service
public class EditarMedicamentoService {
	
	@Autowired
	BuscarMedicamentoPorIdService buscarMedicamento;
	
	@Autowired
	MedicamentoRepository medicamentoRepository;
	
	public void editar(Integer codMedicamento, Medicamento medicamento) {
		Medicamento medicamentoParaEditar = buscarMedicamento.buscar(codMedicamento);
		
		if (!Objects.isNull(medicamento.getNomeMedicamento()) && !medicamento.getNomeMedicamento().isEmpty()) {
			medicamentoParaEditar.setNomeMedicamento(medicamento.getNomeMedicamento());
		}
		
		if (!Objects.isNull(medicamento.getFuncao()) && !medicamento.getFuncao().isEmpty()) {
			medicamentoParaEditar.setFuncao(medicamento.getFuncao());
		}
		
		if (!Objects.isNull(medicamento.getContraIndicacoes()) && !medicamento.getContraIndicacoes().isEmpty()) {
			medicamentoParaEditar.setContraIndicacoes(medicamento.getContraIndicacoes());
		}
		
		if (!Objects.isNull(medicamento.getEfeitosColaterais()) && !medicamento.getEfeitosColaterais().isEmpty()) {
			medicamentoParaEditar.setEfeitosColaterais(medicamento.getEfeitosColaterais());
		}
		
		if (!Objects.isNull(medicamento.getQuantidade())) {
			medicamentoParaEditar.setQuantidade(medicamento.getQuantidade());
		}
		
		if (!Objects.isNull(medicamento.getMedida())) {
			medicamentoParaEditar.setMedida(medicamento.getMedida());
		}
		
		if (!Objects.isNull(medicamento.getFrequenciaDiaria())) {
			medicamentoParaEditar.setFrequenciaDiaria(medicamento.getFrequenciaDiaria());
		}
		
		if (!Objects.isNull(medicamento.getProximoHorario())) {
			medicamentoParaEditar.setProximoHorario(medicamento.getProximoHorario());
		}
		
		if (!Objects.isNull(medicamento.getDuracao())) {
			medicamentoParaEditar.setDuracao(medicamento.getDuracao());
		}
		
		if (!Objects.isNull(medicamento.getTipoDuracao())) {
			medicamentoParaEditar.setTipoDuracao(medicamento.getTipoDuracao());		
		}
		
		medicamentoRepository.save(medicamentoParaEditar);
	}
}
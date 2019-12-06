package com.example.demo.service.medicamento;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Medicamento;
import com.example.demo.repository.MedicamentoRepository;
import com.example.demo.utils.ImageFileWriter;

@Service
public class EditarMedicamentoService {
	
	@Autowired
	BuscarMedicamentoPorIdService buscarMedicamento;
	
	@Autowired
	MedicamentoRepository medicamentoRepository;
	
	public void editar(Medicamento medicamento) throws Exception {
		Medicamento medicamentoParaEditar = buscarMedicamento.buscar(medicamento.getCodMedicamento());
		
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
		
		if (!Objects.isNull(medicamento.getFrequencia())) {
			medicamentoParaEditar.setFrequencia(medicamento.getFrequencia());
		}
		
		if (!Objects.isNull(medicamento.getHorario())) {
			medicamentoParaEditar.setHorario(medicamento.getHorario());
		}
		
		if (!Objects.isNull(medicamento.getFoto()) && !medicamento.getFoto().isEmpty()) {
			if(!medicamentoParaEditar.getFoto().isEmpty()) {
				ImageFileWriter.deleteFile(medicamentoParaEditar.getFoto());
			}
			String base64 = medicamento.getFoto();
			medicamentoParaEditar.setFoto(ImageFileWriter.saveImageToFolder(null, base64));
		}
		
		medicamentoRepository.save(medicamentoParaEditar);
	}
}
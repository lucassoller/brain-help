package com.brain.help.service.medicamento;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.brain.help.entity.Dependente;
import com.brain.help.entity.Medicamento;
import com.brain.help.repository.MedicamentoRepository;
import com.brain.help.service.dependente.BuscarDiagnosticadoPorEmailService;
import com.brain.help.utils.ImageFileWriter;

@Service
public class CadastrarMedicamentoService {
	
	@Autowired
	MedicamentoRepository medicamentoRepository;
	
	@Autowired
	BuscarDiagnosticadoPorEmailService buscarDiagnosticado;

	public void salvar(String emailDiagnosticado, Medicamento medicamento) throws Exception {
		if (Objects.isNull(medicamento.getNomeMedicamento()) || medicamento.getNomeMedicamento().isEmpty()) {
			throw new IllegalArgumentException("O nome do medicamento não pode estar em branco");
		}
		
		if (Objects.isNull(medicamento.getQuantidade())) {
			throw new IllegalArgumentException("A quantidade do medicamento não pode estar em branco");
		}
		
		if (medicamento.getQuantidade() <  1) {
			throw new IllegalArgumentException("A quantidade do medicamento não pode ser inferior a 1");
		}
		
		if (Objects.isNull(medicamento.getMedida())) {
			throw new IllegalArgumentException("A medida do medicamento não pode estar em branco");
		}
		
		if(!Objects.isNull(medicamento.getFoto()) && !medicamento.getFoto().isEmpty()) {
			String base64 = medicamento.getFoto();
			medicamento.setFoto(ImageFileWriter.saveImageToFolder(null, base64));
		}
		
		Dependente dependente = buscarDiagnosticado.buscar(emailDiagnosticado, false);
		medicamento.setDependente(dependente);
		medicamentoRepository.save(medicamento);
	}
}
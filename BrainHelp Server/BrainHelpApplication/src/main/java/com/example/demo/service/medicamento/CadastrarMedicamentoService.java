package com.example.demo.service.medicamento;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Diagnosticado;
import com.example.demo.model.Medicamento;
import com.example.demo.repository.MedicamentoRepository;
import com.example.demo.service.diagnosticado.BuscarDiagnosticadoPorEmailService;
import com.example.demo.utils.ImageFileWriter;

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
		
		if (Objects.isNull(medicamento.getFrequenciaDiaria())) {
			throw new IllegalArgumentException("A frequência diária do medicamento não pode estar em branco");
		}
		
		if (medicamento.getFrequenciaDiaria() < 1) {
			throw new IllegalArgumentException("A frequência diária do medicamento não pode ser inferior a 1");
		}
		
		if(medicamento.getDuracao() > 0 && Objects.isNull(medicamento.getTipoDuracao())) {
			throw new IllegalArgumentException("O tipo da duração do medicamento não pode estar em branco");
		}
		
		if(!Objects.isNull(medicamento.getFoto()) && !medicamento.getFoto().isEmpty()) {
			String base64 = medicamento.getFoto();
			medicamento.setFoto(ImageFileWriter.saveImageToFolder(null, base64));
		}
		
		Diagnosticado diagnosticado = buscarDiagnosticado.buscar(emailDiagnosticado);
		medicamento.setDiagnosticado(diagnosticado);
		medicamentoRepository.save(medicamento);
	}
}
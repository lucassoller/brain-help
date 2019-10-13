package com.example.demo.service.medicamento;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Diagnosticado;
import com.example.demo.model.Medicamento;
import com.example.demo.repository.MedicamentoRepository;
import com.example.demo.service.diagnosticado.BuscarDiagnosticadoPorEmailService;

@Service
public class CadastrarMedicamentoService {
	
	@Autowired
	MedicamentoRepository medicamentoRepository;
	
	@Autowired
	BuscarDiagnosticadoPorEmailService buscarDiagnosticado;

	public void salvar(Diagnosticado diagnosticado, Medicamento medicamento) {
		if (Objects.isNull(medicamento.getNomeMedicamento()) || medicamento.getNomeMedicamento().isEmpty()) {
			throw new IllegalArgumentException("O nome do medicamento n�o pode estar em branco");
		}
		
		if (Objects.isNull(medicamento.getQuantidade())) {
			throw new IllegalArgumentException("A quantidade do medicamento n�o pode estar em branco");
		}
		
		if (medicamento.getQuantidade() <  1) {
			throw new IllegalArgumentException("A quantidade do medicamento n�o pode ser inferior a 1");
		}
		
		if (Objects.isNull(medicamento.getMedida())) {
			throw new IllegalArgumentException("A medida do medicamento n�o pode estar em branco");
		}
		
		if (Objects.isNull(medicamento.getFrequenciaDiaria())) {
			throw new IllegalArgumentException("A frequ�ncia di�ria do medicamento n�o pode estar em branco");
		}
		
		if (medicamento.getFrequenciaDiaria() < 1) {
			throw new IllegalArgumentException("A frequ�ncia di�ria do medicamento n�o pode ser inferior a 1");
		}
		
		if(medicamento.getDuracao() > 0 && Objects.isNull(medicamento.getTipoDuracao())) {
			throw new IllegalArgumentException("O tipo da dura��o do medicamento n�o pode estar em branco");
		}
		
		medicamento.setDiagnosticado(diagnosticado);
		medicamentoRepository.save(medicamento);
	}
	
	public void salvar(String emailDiagnosticado, Medicamento medicamento) {
		Diagnosticado diagnosticado = buscarDiagnosticado.buscar(emailDiagnosticado);
		salvar(diagnosticado, medicamento);
	}
}
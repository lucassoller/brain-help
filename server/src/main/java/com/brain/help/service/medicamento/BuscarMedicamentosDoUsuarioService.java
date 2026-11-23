package com.brain.help.service.medicamento;

import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.brain.help.entity.Dependente;
import com.brain.help.entity.Medicamento;
import com.brain.help.repository.MedicamentoRepository;
import com.brain.help.service.dependente.BuscarDiagnosticadoPorEmailService;
import com.brain.help.utils.ImageFileWriter;

@Service
public class BuscarMedicamentosDoUsuarioService {

	@Autowired
	MedicamentoRepository medicamentoRepository;
	
	@Autowired
	BuscarDiagnosticadoPorEmailService buscarDiagnosticado;

	public List<Medicamento> buscar(String emailDiagnosticado) {
		if (Objects.isNull(emailDiagnosticado) || emailDiagnosticado.isEmpty()) {
			throw new IllegalArgumentException("O email do diagnosticado não pode estar em branco");
		}
		Dependente dependente = buscarDiagnosticado.buscar(emailDiagnosticado, false);
		List<Medicamento> medicamentos = medicamentoRepository.findByDiagnosticado(dependente);
		medicamentos.stream().forEach(medicamento ->{
			try {
				if(!Objects.isNull(medicamento.getFoto()) && !medicamento.getFoto().isEmpty()) {
					medicamento.setFoto(ImageFileWriter.returnBase64FromFile(medicamento.getFoto()));
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		return medicamentos;
	}
}
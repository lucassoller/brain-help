package com.brain.help.service.medico;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.brain.help.entity.Dependente;
import com.brain.help.entity.Medico;
import com.brain.help.repository.DiagnosticadoRepository;
import com.brain.help.service.dependente.BuscarDiagnosticadoPorEmailService;

@Service
public class VincularDiagnosticadoService {
	
	@Autowired
	DiagnosticadoRepository diagnosticadoRepository;

	@Autowired
	BuscarDiagnosticadoPorEmailService buscarDiagnosticado;
	
	@Autowired
	BuscarMedicoPorEmailService buscarMedico;

	public void vincular(String emailMedico, String emailDiagnosticado) {
		if (Objects.isNull(emailMedico) || emailMedico.isEmpty()) {
			throw new IllegalArgumentException("O email do médico não pode estar em branco");
		}
		if (Objects.isNull(emailDiagnosticado) || emailDiagnosticado.isEmpty()) {
			throw new IllegalArgumentException("O email do diagnosticado não pode estar em branco");
		}

		Medico medico = buscarMedico.buscar(emailMedico, false);
		Dependente dependente = buscarDiagnosticado.buscar(emailDiagnosticado, false);
		
		dependente.setMedico(medico);
		
		diagnosticadoRepository.save(dependente);
	}
}
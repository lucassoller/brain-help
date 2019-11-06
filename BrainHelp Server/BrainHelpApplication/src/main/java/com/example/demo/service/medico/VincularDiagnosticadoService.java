package com.example.demo.service.medico;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Diagnosticado;
import com.example.demo.model.Medico;
import com.example.demo.repository.DiagnosticadoRepository;
import com.example.demo.service.diagnosticado.BuscarDiagnosticadoPorEmailService;

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

		Medico medico = buscarMedico.buscar(emailMedico);
		Diagnosticado diagnosticado = buscarDiagnosticado.buscar(emailDiagnosticado);
		
		diagnosticado.setMedico(medico);
		
		diagnosticadoRepository.save(diagnosticado);
	}
}
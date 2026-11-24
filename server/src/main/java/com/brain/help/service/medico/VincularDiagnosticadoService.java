package com.brain.help.service.medico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VincularDiagnosticadoService {
	@Autowired
	MedicoService medicoService;

	public void vincular(String emailMedico, String emailDiagnosticado) {
		medicoService.vincular(emailMedico, emailDiagnosticado);
	}
}

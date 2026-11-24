package com.brain.help.service.medico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeletarMedicoService {
	@Autowired
	MedicoService medicoService;

	public void deletar(String emailMedico) {
		medicoService.deletar(emailMedico);
	}
}

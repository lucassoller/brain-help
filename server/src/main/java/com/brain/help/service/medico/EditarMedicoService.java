package com.brain.help.service.medico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.brain.help.entity.Medico;

@Service
public class EditarMedicoService {
	@Autowired
	MedicoService medicoService;

	public void editar(String email, Medico medico) throws Exception {
		medicoService.editar(email, medico);
	}
}

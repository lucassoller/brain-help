package com.brain.help.service.medico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.brain.help.entity.Medico;

@Service
public class BuscarMedicoPorEmailService {
	@Autowired
	MedicoService medicoService;

	public Medico buscar(String emailMedico, boolean foto) {
		return medicoService.buscar(emailMedico, foto);
	}
}

package com.brain.help.service.medico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuscarEmailMedicoUsadoService {
	@Autowired
	MedicoService medicoService;

	public void buscar(String email) {
		medicoService.buscarEmailUsado(email);
	}
}

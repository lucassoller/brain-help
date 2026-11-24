package com.brain.help.service.medico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.brain.help.entity.Medico;

@Service
public class CadastrarMedicoService {
	@Autowired
	MedicoService medicoService;

	public void salvar(Medico medico) throws Exception {
		medicoService.salvar(medico);
	}
}

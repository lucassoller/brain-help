package com.brain.help.service.medico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.brain.help.entity.Medico;
import com.brain.help.repository.MedicoRepository;

@Service
public class DeletarMedicoService {

	@Autowired
	BuscarMedicoPorEmailService buscarMedico;
	
	@Autowired
	MedicoRepository medicoRepository;
	
	public void deletar(String emailMedico) {
		Medico medico = buscarMedico.buscar(emailMedico, false);
		medicoRepository.delete(medico);
	}
}

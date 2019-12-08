package com.example.demo.service.medico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Medico;
import com.example.demo.repository.MedicoRepository;

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

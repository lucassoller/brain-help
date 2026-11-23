package com.brain.help.service.medico;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.brain.help.entity.Medico;
import com.brain.help.repository.MedicoRepository;

@Service
public class BuscarMedicoPorEmailEGoogleService {

	@Autowired
	MedicoRepository medicoRepository;

	public Medico buscar(String emailMedico, boolean google) {
		if ((Objects.isNull(emailMedico) || emailMedico.isEmpty())) {
			throw new IllegalArgumentException("A identificação não pode estar em branco");
		}		
		
		return medicoRepository.findByEmailAndGoogle(emailMedico, google)
				.orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
	}
}
package com.example.demo.service.medico;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Medico;
import com.example.demo.repository.MedicoRepository;

@Service
public class BuscarMedicoPorEmailEGoogleService {

	@Autowired
	MedicoRepository medicoRepository;

	public Medico buscar(String emailMedico, boolean google) {
		if ((Objects.isNull(emailMedico) || emailMedico.isEmpty())) {
			throw new IllegalArgumentException("A identifica��o n�o pode estar em branco");
		}		
		
		return medicoRepository.findByEmailAndGoogle(emailMedico, google)
				.orElseThrow(() -> new IllegalArgumentException("Usu�rio n�o encontrado"));
	}
}
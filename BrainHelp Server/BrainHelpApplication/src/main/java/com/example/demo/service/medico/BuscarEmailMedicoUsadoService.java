package com.example.demo.service.medico;

import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Medico;
import com.example.demo.repository.MedicoRepository;

@Service
public class BuscarEmailMedicoUsadoService {
	@Autowired
	MedicoRepository medicoRepository;
	
	public void buscar(String email) {
		if ((Objects.isNull(email) || email.isEmpty())) {
			throw new IllegalArgumentException("O email não pode estar em branco");
		}
		
		Optional<Medico> medico = medicoRepository.findByEmail(email);
		if(medico.isPresent()) {
			throw new IllegalArgumentException("O email já está em uso");
		}
	}
}

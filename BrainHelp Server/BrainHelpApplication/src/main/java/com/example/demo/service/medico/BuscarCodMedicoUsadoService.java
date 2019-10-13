package com.example.demo.service.medico;

import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Medico;
import com.example.demo.repository.MedicoRepository;

@Service
public class BuscarCodMedicoUsadoService {
	@Autowired
	MedicoRepository medicoRepository;
	
	public void buscar(String codMedico) {
		if ((Objects.isNull(codMedico) || codMedico.isEmpty())) {
			throw new IllegalArgumentException("O c�digo de identifica��o n�o pode estar em branco");
		}
		
		Optional<Medico> medico = medicoRepository.findByCodMedico(codMedico);
		if(medico.isPresent()) {
			throw new IllegalArgumentException("O c�digo de identifica��o j� est� em uso");
		}
	}
}
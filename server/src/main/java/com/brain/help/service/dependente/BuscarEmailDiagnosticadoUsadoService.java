package com.brain.help.service.dependente;

import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.brain.help.entity.Dependente;
import com.brain.help.repository.DependenteRepository;

@Service
public class BuscarEmailDiagnosticadoUsadoService {
	@Autowired
    DependenteRepository dependenteRepository;
	
	public void buscar(String email) {
		if ((Objects.isNull(email) || email.isEmpty())) {
			throw new IllegalArgumentException("O email não pode estar em branco");
		}
		
		Optional<Dependente> dianosticado = dependenteRepository.findByEmail(email);
		if(dianosticado.isPresent()) {
			throw new IllegalArgumentException("O email já está em uso");
		}
	}
}

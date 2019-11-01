package com.example.demo.service.diagnosticado;

import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Diagnosticado;
import com.example.demo.repository.DiagnosticadoRepository;

@Service
public class BuscarEmailDiagnosticadoUsadoService {
	@Autowired
	DiagnosticadoRepository diagnosticadoRepository;
	
	public void buscar(String email) {
		if ((Objects.isNull(email) || email.isEmpty())) {
			throw new IllegalArgumentException("O email não pode estar em branco");
		}
		
		Optional<Diagnosticado> dianosticado = diagnosticadoRepository.findByEmail(email);
		if(dianosticado.isPresent()) {
			throw new IllegalArgumentException("O email já está em uso");
		}
	}
}

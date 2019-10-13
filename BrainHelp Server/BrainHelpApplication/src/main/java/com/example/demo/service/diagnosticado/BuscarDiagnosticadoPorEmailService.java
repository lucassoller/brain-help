package com.example.demo.service.diagnosticado;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Diagnosticado;
import com.example.demo.repository.DiagnosticadoRepository;

@Service
public class BuscarDiagnosticadoPorEmailService {

	@Autowired
	DiagnosticadoRepository diagnosticadoRepository;

	public Diagnosticado buscar(String email) {
		if ((Objects.isNull(email) || email.isEmpty())) {
			throw new IllegalArgumentException("O email não pode estar em branco");
		}
		
		return diagnosticadoRepository.findByEmail(email)
				.orElseThrow(() -> new IllegalArgumentException("Nenhum diagnosticado foi encontrado"));
	}
}

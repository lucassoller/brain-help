package com.example.demo.service.diagnosticado;

import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Diagnosticado;
import com.example.demo.repository.DiagnosticadoRepository;

@Service
public class BuscarDiagnosticadosPorEmailService {

	@Autowired
	DiagnosticadoRepository diagnosticadoRepository;

	public List<Diagnosticado> buscar(String email) {
		if ((Objects.isNull(email) || email.isEmpty())) {
			throw new IllegalArgumentException("O email n�o pode estar em branco");
		}
		
		return diagnosticadoRepository.findAllByEmailContaining(email);
	}
}

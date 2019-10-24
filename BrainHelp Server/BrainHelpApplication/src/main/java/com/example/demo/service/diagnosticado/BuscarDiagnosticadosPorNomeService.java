package com.example.demo.service.diagnosticado;

import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Diagnosticado;
import com.example.demo.repository.DiagnosticadoRepository;

@Service
public class BuscarDiagnosticadosPorNomeService {

	@Autowired
	DiagnosticadoRepository diagnosticadoRepository;

	public List<Diagnosticado> buscar(String nome) {
		if ((Objects.isNull(nome) || nome.isEmpty())) {
			throw new IllegalArgumentException("O nome não pode estar em branco");
		}
		
		return diagnosticadoRepository.findAllByNomeContaining(nome);
	}
}

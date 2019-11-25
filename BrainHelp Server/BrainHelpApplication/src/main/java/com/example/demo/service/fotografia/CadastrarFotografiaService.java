package com.example.demo.service.fotografia;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Diagnosticado;
import com.example.demo.model.Fotografia;
import com.example.demo.repository.FotografiaRepository;
import com.example.demo.service.diagnosticado.BuscarDiagnosticadoPorEmailService;

@Service
public class CadastrarFotografiaService {
	
	@Autowired
	FotografiaRepository fotografiaRepository;
	
	@Autowired
	BuscarDiagnosticadoPorEmailService buscarDiagnosticado;
	

	public void salvar(String emailDiagnosticado, Fotografia fotografia) {
		if (Objects.isNull(fotografia.getLugar()) || fotografia.getLugar().isEmpty()) {
			throw new IllegalArgumentException("O lugar não pode estar em branco");
		}
		
		if (Objects.isNull(fotografia.getDescricao()) || fotografia.getDescricao().isEmpty()) {
			throw new IllegalArgumentException("A descrição da fotografia não pode estar em branco");
		}
		
		Diagnosticado diagnosticado = buscarDiagnosticado.buscar(emailDiagnosticado);
		
		fotografia.setDiagnosticado(diagnosticado);
		fotografiaRepository.save(fotografia);
	}
}
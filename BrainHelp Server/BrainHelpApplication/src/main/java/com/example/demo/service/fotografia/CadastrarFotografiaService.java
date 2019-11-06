package com.example.demo.service.fotografia;

import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Diagnosticado;
import com.example.demo.model.Fotografia;
import com.example.demo.model.Lembranca;
import com.example.demo.repository.FotografiaRepository;
import com.example.demo.service.diagnosticado.BuscarDiagnosticadoPorEmailService;
import com.example.demo.service.lembranca.CadastrarLembrancaService;

@Service
public class CadastrarFotografiaService {
	
	@Autowired
	FotografiaRepository fotografiaRepository;

	@Autowired
	CadastrarLembrancaService cadastrarLembrancaService;
	
	@Autowired
	BuscarDiagnosticadoPorEmailService buscarDiagnosticado;
	

	public void salvar(Diagnosticado diagnosticado, Fotografia fotografia) {
		if (Objects.isNull(fotografia.getLugar()) || fotografia.getLugar().isEmpty()) {
			throw new IllegalArgumentException("O lugar não pode estar em branco");
		}
		
		if (Objects.isNull(fotografia.getDescricao()) || fotografia.getDescricao().isEmpty()) {
			throw new IllegalArgumentException("A descrição da fotografia não pode estar em branco");
		}
		
		fotografia.setDiagnosticado(diagnosticado);
		fotografiaRepository.save(fotografia);

		List<Lembranca> lembrancas = fotografia.getLembrancas();
		if(!Objects.isNull(lembrancas) && !lembrancas.isEmpty()) {
			for(Lembranca lembranca : lembrancas) {
				cadastrarLembrancaService.salvar(lembranca, fotografia);
			}
		}
	}
	
	public void salvar(String emailDiagnosticado, Fotografia fotografia) {
		Diagnosticado diagnosticado = buscarDiagnosticado.buscar(emailDiagnosticado);
		salvar(diagnosticado, fotografia);
	}
}
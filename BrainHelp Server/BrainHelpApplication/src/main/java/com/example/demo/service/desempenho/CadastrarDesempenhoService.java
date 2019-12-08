package com.example.demo.service.desempenho;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Desempenho;
import com.example.demo.model.Diagnosticado;
import com.example.demo.repository.DesempenhoRepository;
import com.example.demo.service.diagnosticado.BuscarDiagnosticadoPorEmailService;

@Service
public class CadastrarDesempenhoService {
	
	@Autowired
	DesempenhoRepository desempenhoRepository;
	
	@Autowired
	BuscarDiagnosticadoPorEmailService buscarDiagnosticado;
	
	public void salvar(String emailDiagnosticado, Desempenho desempenho) {
		
		if (Objects.isNull(desempenho.getAtividade()) || desempenho.getAtividade().isEmpty()) {
			throw new IllegalArgumentException("O código da atividade não pode estar em branco");
		}
		
		if(desempenho.getPontuacao() < 0 || desempenho.getPontuacao() > 100) {
			throw new IllegalArgumentException("A pontuação do desempenho deve estar entre 0 e 100 pontos");
		}
		
		if(Objects.isNull(desempenho.getAvaliacaoDesempenho())) {
			throw new IllegalArgumentException("A avaliação do desempenho não pode estar em branco");
		}
		
		Diagnosticado diagnosticado = buscarDiagnosticado.buscar(emailDiagnosticado, false);
		desempenho.setDiagnosticado(diagnosticado);
		
		desempenhoRepository.save(desempenho);
	}
}
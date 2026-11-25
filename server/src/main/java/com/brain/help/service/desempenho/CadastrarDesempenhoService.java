package com.brain.help.service.desempenho;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.brain.help.entity.Desempenho;
import com.brain.help.entity.Dependente;
import com.brain.help.repository.DesempenhoRepository;

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
		
		Dependente dependente = buscarDiagnosticado.buscar(emailDiagnosticado, false);
		desempenho.setDependente(dependente);
		
		desempenhoRepository.save(desempenho);
	}
}
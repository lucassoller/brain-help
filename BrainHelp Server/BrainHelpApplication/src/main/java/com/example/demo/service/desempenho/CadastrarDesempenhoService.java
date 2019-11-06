package com.example.demo.service.desempenho;

import java.util.Date;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Atividade;
import com.example.demo.model.Desempenho;
import com.example.demo.model.Diagnosticado;
import com.example.demo.model.enumerated.AvaliacaoDesempenho;
import com.example.demo.repository.DesempenhoRepository;
import com.example.demo.service.atividade.BuscarAtividadePorIdService;
import com.example.demo.service.diagnosticado.BuscarDiagnosticadoPorEmailService;

@Service
public class CadastrarDesempenhoService {
	
	@Autowired
	DesempenhoRepository desempenhoRepository;
	
	@Autowired
	BuscarAtividadePorIdService buscarAtividadeService;
	
	@Autowired
	BuscarDiagnosticadoPorEmailService buscarDiagnosticado;
	
	public void salvar(String emailDiagnosticado, Integer codAtividade) {
		
		if (Objects.isNull(codAtividade)) {
			throw new IllegalArgumentException("O código da atividade não pode estar em branco");
		}
		
		Desempenho desempenho = new Desempenho();
		desempenho.setData(new Date());
		desempenho.setAvaliacaoDesempenho(AvaliacaoDesempenho.BOA);
		desempenho.setPontuacao(0);
		
		Atividade atividade = buscarAtividadeService.buscar(codAtividade);
		desempenho.setAtividade(atividade);
		
		Diagnosticado diagnosticado = buscarDiagnosticado.buscar(emailDiagnosticado);
		desempenho.setDiagnosticado(diagnosticado);
		
		desempenhoRepository.save(desempenho);
	}
}
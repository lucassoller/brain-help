package com.example.demo.service.atividade;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Atividade;
import com.example.demo.repository.AtividadeRepository;

@Service
public class CadastrarAtividadeService {
	
	@Autowired
	AtividadeRepository atividadeRepository;

	public void salvar(Atividade atividade) {
		if (Objects.isNull(atividade.getNome()) || atividade.getNome().isEmpty()) {
			throw new IllegalArgumentException("O nome da atividade não pode estar em branco");
		}
		
		if (Objects.isNull(atividade.getDescricao()) || atividade.getDescricao().isEmpty()) {
			throw new IllegalArgumentException("A descrição da atividade não pode estar em branco");
		}
		
		if (Objects.isNull(atividade.getPontuacaoTotal())) {
			throw new IllegalArgumentException("A pontuação da atividade não pode estar em branco");
		}
		
		if (atividade.getPontuacaoTotal() <= 0) {
			throw new IllegalArgumentException("A pontuação da atividade não pode ser igual ou inferior a 0");
		}
		
		if (Objects.isNull(atividade.getTipoAtividade())) {
			throw new IllegalArgumentException("O tipo da atividade não pode estar em branco");
		}
	}
}

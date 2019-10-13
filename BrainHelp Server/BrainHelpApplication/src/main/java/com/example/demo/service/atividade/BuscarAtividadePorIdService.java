package com.example.demo.service.atividade;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Atividade;
import com.example.demo.repository.AtividadeRepository;

@Service
public class BuscarAtividadePorIdService {

	@Autowired
	AtividadeRepository atividadeRepository;

	public Atividade buscar(Integer codAtividade) {
		if ((Objects.isNull(codAtividade))) {
			throw new IllegalArgumentException("O código da atividade não pode estar em branco");
		}
		
		return atividadeRepository.findById(codAtividade)
				.orElseThrow(() -> new IllegalArgumentException("Nenhuma atividade foi encontrada"));
	}
}

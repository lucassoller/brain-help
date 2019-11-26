package com.example.demo.service.atividade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Atividade;
import com.example.demo.repository.AtividadeRepository;

@Service
public class DeletarAtividadeService {

	@Autowired
	BuscarAtividadePorIdService buscarAtividade;
	
	@Autowired
	AtividadeRepository atividadeRepository;
	
	public void deletar(Integer codAtividade) {
		Atividade atividade = buscarAtividade.buscar(codAtividade);
		atividadeRepository.delete(atividade);
	}
}
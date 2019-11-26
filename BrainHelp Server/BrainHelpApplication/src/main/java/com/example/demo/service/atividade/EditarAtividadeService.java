package com.example.demo.service.atividade;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Atividade;
import com.example.demo.repository.AtividadeRepository;

@Service
public class EditarAtividadeService {
	
	@Autowired
	BuscarAtividadePorIdService buscarAtividade;
	
	@Autowired
	AtividadeRepository atividadeRepository;
	
	public void editar(Integer codAtividade, Atividade atividade) {
		Atividade atividadeParaEditar = buscarAtividade.buscar(codAtividade);
		
		if (!Objects.isNull(atividade.getNome()) && !atividade.getNome().isEmpty()) {
			atividadeParaEditar.setNome(atividade.getNome());
		}
		
		if (!Objects.isNull(atividade.getDescricao()) && !atividade.getDescricao().isEmpty()) {
			atividadeParaEditar.setDescricao(atividade.getDescricao());
		}
		
		if (!Objects.isNull(atividade.getPontuacaoTotal())) {
			atividadeParaEditar.setPontuacaoTotal(atividade.getPontuacaoTotal());
		}
		
		if (!Objects.isNull(atividade.getTipoAtividade())) {
			atividadeParaEditar.setTipoAtividade(atividade.getTipoAtividade());
		}
		
		atividadeRepository.save(atividadeParaEditar);
	}
}
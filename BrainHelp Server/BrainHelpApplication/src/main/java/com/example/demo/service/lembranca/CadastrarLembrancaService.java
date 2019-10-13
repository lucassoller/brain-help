package com.example.demo.service.lembranca;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Fotografia;
import com.example.demo.model.Lembranca;
import com.example.demo.model.Musica;
import com.example.demo.model.Vinculo;
import com.example.demo.repository.LembrancaRepository;

@Service
public class CadastrarLembrancaService {
	
	@Autowired
	LembrancaRepository lembrancaRepository;	
	
	@Autowired
	CadastrarLembrancaService cadastrarLembranca;	

	public void salvar(Lembranca lembranca) {
		if (Objects.isNull(lembranca.getTitulo()) || lembranca.getTitulo().isEmpty()) {
			throw new IllegalArgumentException("O título da lembrança não pode estar em branco");
		}
		
		if (Objects.isNull(lembranca.getLembranca()) || lembranca.getLembranca().isEmpty()) {
			throw new IllegalArgumentException("A lembrança não pode estar em branco");
		}
		
		lembrancaRepository.save(lembranca);
	}
	
	public void salvar(Lembranca lembranca, Vinculo vinculo) {
		lembranca.setVinculo(vinculo);
		salvar(lembranca);
	}
	
	public void salvar(Lembranca lembranca, Musica musica) {
		lembranca.setMusica(musica);
		salvar(lembranca);
	}
	
	public void salvar(Lembranca lembranca, Fotografia fotografia) {
		lembranca.setFotografia(fotografia);
		salvar(lembranca);
	}
}
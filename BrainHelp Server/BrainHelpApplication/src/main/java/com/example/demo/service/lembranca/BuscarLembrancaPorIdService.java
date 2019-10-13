package com.example.demo.service.lembranca;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Lembranca;
import com.example.demo.repository.LembrancaRepository;

@Service
public class BuscarLembrancaPorIdService {

	@Autowired
	LembrancaRepository lembrancaRepository;

	public Lembranca buscar(Integer codLembranca) {
		if ((Objects.isNull(codLembranca))) {
			throw new IllegalArgumentException("O c�digo da lembran�a n�o pode estar em branco");
		}
		
		return lembrancaRepository.findById(codLembranca)
				.orElseThrow(() -> new IllegalArgumentException("Nenhuma lembran�a foi encontrada"));
	}
}

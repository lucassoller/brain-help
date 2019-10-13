package com.example.demo.service.desempenho;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Desempenho;
import com.example.demo.repository.DesempenhoRepository;

@Service
public class BuscarDesempenhoPorIdService {

	@Autowired
	DesempenhoRepository desempenhoRepository;

	public Desempenho buscar(Integer codDesempenho) {
		if ((Objects.isNull(codDesempenho))) {
			throw new IllegalArgumentException("O código do desempenho não pode estar em branco");
		}
		
		return desempenhoRepository.findById(codDesempenho)
				.orElseThrow(() -> new IllegalArgumentException("Nenhum desempenho foi encontrado"));
	}
}

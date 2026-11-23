package com.brain.help.service.fotografia;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.brain.help.entity.Fotografia;
import com.brain.help.repository.FotografiaRepository;

@Service
public class BuscarFotografiaPorIdService {

	@Autowired
	FotografiaRepository fotografiaRepository;

	public Fotografia buscar(Integer codFotografia) {
		if ((Objects.isNull(codFotografia))) {
			throw new IllegalArgumentException("O código da fotografia não pode estar em branco");
		}
		
		return fotografiaRepository.findById(codFotografia)
				.orElseThrow(() -> new IllegalArgumentException("Nenhuma fotografia foi encontrada"));
	}
}

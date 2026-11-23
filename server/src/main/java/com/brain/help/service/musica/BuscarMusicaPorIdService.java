package com.brain.help.service.musica;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.brain.help.entity.Musica;
import com.brain.help.repository.MusicaRepository;

@Service
public class BuscarMusicaPorIdService {

	@Autowired
	MusicaRepository musicaRepository;

	public Musica buscar(Integer codMusica) {
		if ((Objects.isNull(codMusica))) {
			throw new IllegalArgumentException("O código da música não pode estar em branco");
		}
		
		return musicaRepository.findById(codMusica)
				.orElseThrow(() -> new IllegalArgumentException("Nenhuma música foi encontrada"));
	}
}
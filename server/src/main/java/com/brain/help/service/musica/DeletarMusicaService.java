package com.brain.help.service.musica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.brain.help.entity.Musica;
import com.brain.help.repository.MusicaRepository;

@Service
public class DeletarMusicaService {

	@Autowired
	BuscarMusicaPorIdService buscarMusica;
	
	@Autowired
	MusicaRepository musicaRepository;
	
	public void deletar(Integer codMusica) {
		Musica musica = buscarMusica.buscar(codMusica);
		musicaRepository.delete(musica);
	}
}
package com.example.demo.service.musica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Musica;
import com.example.demo.repository.MusicaRepository;

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
package com.example.demo.service.musica;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Musica;
import com.example.demo.repository.MusicaRepository;

@Service
public class EditarMusicaService {
	
	@Autowired
	BuscarMusicaPorIdService buscarMusica;
	
	@Autowired
	MusicaRepository musicaRepository;
	
	public void editar(Integer codMusica, Musica musica) {
		Musica musicaParaEditar = buscarMusica.buscar(codMusica);
		
		if (!Objects.isNull(musica.getNome()) && !musica.getNome().isEmpty()) {
			musicaParaEditar.setNome(musica.getNome());
		}
		
		if (!Objects.isNull(musica.getCantor()) && !musica.getCantor().isEmpty()) {
			musicaParaEditar.setCantor(musica.getCantor());
		}
		
		if (!Objects.isNull(musica.getLembrancas()) && !musica.getLembrancas().isEmpty()) {
			musicaParaEditar.setLembrancas(musica.getLembrancas());
		}
		
		if (!Objects.isNull(musica.getMusica()) && !musica.getMusica().isEmpty()) {
			musicaParaEditar.setMusica(musica.getMusica());
		}
		
		musicaRepository.save(musicaParaEditar);
	}
}
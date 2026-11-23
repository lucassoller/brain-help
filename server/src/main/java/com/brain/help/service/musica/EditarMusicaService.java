package com.brain.help.service.musica;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.brain.help.entity.Musica;
import com.brain.help.repository.MusicaRepository;

@Service
public class EditarMusicaService {
	
	@Autowired
	BuscarMusicaPorIdService buscarMusica;
	
	@Autowired
	MusicaRepository musicaRepository;
	
	public void editar(Musica musica) {
		Musica musicaParaEditar = buscarMusica.buscar(musica.getCodMusica());
		
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
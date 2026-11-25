package com.brain.help.service.musica;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.brain.help.entity.Dependente;
import com.brain.help.entity.Musica;
import com.brain.help.repository.MusicaRepository;

@Service
public class CadastrarMusicaService {
	
	@Autowired
	MusicaRepository musicaRepository;	
	
	@Autowired
	BuscarDiagnosticadoPorEmailService buscarDiagnosticado;

	public void salvar(String emailDiagnosticado, Musica musica) {
		
		if (Objects.isNull(musica.getNome()) || musica.getNome().isEmpty()) {
			throw new IllegalArgumentException("O nome da música não pode estar em branco");
		}
		
		if (Objects.isNull(musica.getCantor()) || musica.getCantor().isEmpty()) {
			throw new IllegalArgumentException("O nome do cantor não pode estar em branco");
		}
		
		Dependente dependente = buscarDiagnosticado.buscar(emailDiagnosticado, false);
		musica.setDependente(dependente);
		musicaRepository.save(musica);
	}
}
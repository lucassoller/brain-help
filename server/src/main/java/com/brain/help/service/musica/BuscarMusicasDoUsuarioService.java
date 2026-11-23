package com.brain.help.service.musica;

import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.brain.help.entity.Dependente;
import com.brain.help.entity.Musica;
import com.brain.help.repository.MusicaRepository;
import com.brain.help.service.dependente.BuscarDiagnosticadoPorEmailService;

@Service
public class BuscarMusicasDoUsuarioService {

	@Autowired
	MusicaRepository musicaRepository;
	
	@Autowired
	BuscarDiagnosticadoPorEmailService buscarDiagnosticado;

	public List<Musica> buscar(String emailDiagnosticado) {
		if (Objects.isNull(emailDiagnosticado) || emailDiagnosticado.isEmpty()) {
			throw new IllegalArgumentException("O email do diagnosticado não pode estar em branco");
		}
		Dependente dependente = buscarDiagnosticado.buscar(emailDiagnosticado, false);
		return musicaRepository.findByDiagnosticado(dependente);
	}
}
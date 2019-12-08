package com.example.demo.service.musica;

import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Diagnosticado;
import com.example.demo.model.Musica;
import com.example.demo.repository.MusicaRepository;
import com.example.demo.service.diagnosticado.BuscarDiagnosticadoPorEmailService;

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
		Diagnosticado diagnosticado = buscarDiagnosticado.buscar(emailDiagnosticado, false);
		return musicaRepository.findByDiagnosticado(diagnosticado);
	}
}
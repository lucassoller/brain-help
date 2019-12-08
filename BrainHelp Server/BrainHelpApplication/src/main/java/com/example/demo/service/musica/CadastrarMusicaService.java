package com.example.demo.service.musica;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Diagnosticado;
import com.example.demo.model.Musica;
import com.example.demo.repository.MusicaRepository;
import com.example.demo.service.diagnosticado.BuscarDiagnosticadoPorEmailService;

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
		
		Diagnosticado diagnosticado = buscarDiagnosticado.buscar(emailDiagnosticado, false);
		musica.setDiagnosticado(diagnosticado);
		musicaRepository.save(musica);
	}
}
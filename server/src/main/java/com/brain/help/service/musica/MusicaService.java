package com.brain.help.service.musica;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.brain.help.entity.Musica;

@Service
public class MusicaService {
	@Autowired
	BuscarMusicaPorIdService buscarPorId;
	@Autowired
	BuscarMusicasDoUsuarioService buscarDoUsuario;
	@Autowired
	CadastrarMusicaService cadastrar;
	@Autowired
	EditarMusicaService editar;
	@Autowired
	DeletarMusicaService deletar;

	public Musica buscar(Integer codMusica) {
		return buscarPorId.buscar(codMusica);
	}

	public List<Musica> buscar(String emailDiagnosticado) {
		return buscarDoUsuario.buscar(emailDiagnosticado);
	}

	public void salvar(String emailDiagnosticado, Musica musica) {
		cadastrar.salvar(emailDiagnosticado, musica);
	}

	public void editar(Musica musica) {
		editar.editar(musica);
	}

	public void deletar(Integer codMusica) {
		deletar.deletar(codMusica);
	}
}

package com.brain.help.service.fotografia;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.brain.help.entity.Fotografia;

@Service
public class FotografiaService {
	@Autowired
	BuscarFotografiaPorIdService buscarFotografiaPorId;

	@Autowired
	BuscarFotografiasDoUsuarioService buscarFotografiasDoUsuario;

	@Autowired
	CadastrarFotografiaService cadastrarFotografia;

	@Autowired
	EditarFotografiaService editarFotografia;

	@Autowired
	DeletarFotografiaService deletarFotografia;

	public Fotografia buscar(Integer codFotografia) {
		return buscarFotografiaPorId.buscar(codFotografia);
	}

	public List<Fotografia> buscar(String emailDiagnosticado) {
		return buscarFotografiasDoUsuario.buscar(emailDiagnosticado);
	}

	public void salvar(String emailDiagnosticado, Fotografia fotografia) throws Exception {
		cadastrarFotografia.salvar(emailDiagnosticado, fotografia);
	}

	public void editar(Fotografia fotografia) throws Exception {
		editarFotografia.editar(fotografia);
	}

	public void deletar(Integer codFotografia) {
		deletarFotografia.deletar(codFotografia);
	}
}

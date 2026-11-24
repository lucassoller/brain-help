package com.brain.help.service.vinculo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.brain.help.entity.Vinculo;

@Service
public class VinculoService {
	@Autowired
	BuscarVinculoPorIdService buscarPorId;
	@Autowired
	BuscarVinculosDoUsuarioService buscarDoUsuario;
	@Autowired
	CadastrarVinculoService cadastrar;
	@Autowired
	EditarVinculoService editar;
	@Autowired
	DeletarVinculoService deletar;

	public Vinculo buscar(Integer codVinculo) {
		return buscarPorId.buscar(codVinculo);
	}

	public List<Vinculo> buscar(String emailDiagnosticado) {
		return buscarDoUsuario.buscar(emailDiagnosticado);
	}

	public void salvar(String emailDiagnosticado, Vinculo vinculo) throws Exception {
		cadastrar.salvar(emailDiagnosticado, vinculo);
	}

	public void editar(Vinculo vinculo) throws Exception {
		editar.editar(vinculo);
	}

	public void deletar(Integer codVinculo) {
		deletar.deletar(codVinculo);
	}
}

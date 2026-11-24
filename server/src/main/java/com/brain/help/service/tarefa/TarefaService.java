package com.brain.help.service.tarefa;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.brain.help.entity.Tarefa;

@Service
public class TarefaService {
	@Autowired
	BuscarTarefaPorIdService buscarPorId;
	@Autowired
	BuscarTarefasDoUsuarioService buscarDoUsuario;
	@Autowired
	CadastrarTarefaService cadastrar;
	@Autowired
	EditarTarefaService editar;
	@Autowired
	DeletarTarefaService deletar;

	public Tarefa buscar(Integer codTarefa) {
		return buscarPorId.buscar(codTarefa);
	}

	public List<Tarefa> buscar(String emailDiagnosticado) {
		return buscarDoUsuario.buscar(emailDiagnosticado);
	}

	public void salvar(String emailDiagnosticado, Tarefa tarefa) {
		cadastrar.salvar(emailDiagnosticado, tarefa);
	}

	public void editar(Tarefa tarefa) {
		editar.editar(tarefa);
	}

	public void deletar(Integer codTarefa) {
		deletar.deletar(codTarefa);
	}
}

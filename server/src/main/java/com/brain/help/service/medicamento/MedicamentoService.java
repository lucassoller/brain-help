package com.brain.help.service.medicamento;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.brain.help.entity.Medicamento;

@Service
public class MedicamentoService {
	@Autowired
	BuscarMedicamentoPorIdService buscarMedicamentoPorId;

	@Autowired
	BuscarMedicamentosDoUsuarioService buscarMedicamentosDoUsuario;

	@Autowired
	CadastrarMedicamentoService cadastrarMedicamento;

	@Autowired
	EditarMedicamentoService editarMedicamento;

	@Autowired
	DeletarMedicamentoService deletarMedicamento;

	public Medicamento buscar(Integer codMedicamento) {
		return buscarMedicamentoPorId.buscar(codMedicamento);
	}

	public List<Medicamento> buscar(String emailDiagnosticado) {
		return buscarMedicamentosDoUsuario.buscar(emailDiagnosticado);
	}

	public void salvar(String emailDiagnosticado, Medicamento medicamento) throws Exception {
		cadastrarMedicamento.salvar(emailDiagnosticado, medicamento);
	}

	public void editar(Medicamento medicamento) throws Exception {
		editarMedicamento.editar(medicamento);
	}

	public void deletar(Integer codMedicamento) {
		deletarMedicamento.deletar(codMedicamento);
	}
}

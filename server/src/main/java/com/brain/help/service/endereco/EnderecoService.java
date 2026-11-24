package com.brain.help.service.endereco;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.brain.help.entity.Endereco;

@Service
public class EnderecoService {
	@Autowired
	BuscarEnderecoPorIdService buscarEnderecoPorId;

	@Autowired
	BuscarEnderecosDoUsuarioService buscarEnderecosDoUsuario;

	@Autowired
	CadastrarEnderecoService cadastrarEndereco;

	@Autowired
	CadastrarListaEnderecoService cadastrarListaEndereco;

	@Autowired
	EditarEnderecoService editarEndereco;

	@Autowired
	DeletarEnderecoService deletarEndereco;

	public Endereco buscar(Integer codEndereco) {
		return buscarEnderecoPorId.buscar(codEndereco);
	}

	public List<Endereco> buscar(String emailDiagnosticado) {
		return buscarEnderecosDoUsuario.buscar(emailDiagnosticado);
	}

	public void salvar(Endereco endereco) {
		cadastrarEndereco.salvar(endereco);
	}

	public void salvar(Endereco endereco, String email) throws Exception {
		cadastrarListaEndereco.salvar(endereco, email);
	}

	public void editar(Endereco endereco) throws Exception {
		editarEndereco.editar(endereco);
	}

	public void deletar(Integer codEndereco) {
		deletarEndereco.deletar(codEndereco);
	}
}

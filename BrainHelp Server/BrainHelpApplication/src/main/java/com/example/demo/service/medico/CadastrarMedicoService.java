package com.example.demo.service.medico;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Medico;
import com.example.demo.model.security.password.Criptografia;
import com.example.demo.repository.MedicoRepository;
import com.example.demo.service.endereco.CadastrarEnderecoService;

@Service
public class CadastrarMedicoService {
	
	@Autowired
	MedicoRepository medicoRepository;

	@Autowired
	BuscarEmailMedicoUsadoService buscarEmailUsadoService;
	
	@Autowired
	CadastrarEnderecoService cadastrarEndereco;

	public void salvar(Medico medico) {
		if (Objects.isNull(medico.getNome()) || medico.getNome().isEmpty()) {
			throw new IllegalArgumentException("O nome não pode estar em branco");
		}
		if (Objects.isNull(medico.getSobrenome()) || medico.getSobrenome().isEmpty()) {
			throw new IllegalArgumentException("O sobrenome não pode estar em branco");
		}
		if (Objects.isNull(medico.getEmail()) || medico.getEmail().isEmpty()) {
			throw new IllegalArgumentException("O email não pode estar em branco");
		}
		if (Objects.isNull(medico.getSenha()) || medico.getSenha().isEmpty()) {
			throw new IllegalArgumentException("A senha não pode estar em branco");
		}
		if (Objects.isNull(medico.getTelefone()) || medico.getTelefone().isEmpty()) {
			throw new IllegalArgumentException("O telefone não pode estar em branco");
		}
		if (Objects.isNull(medico.getEspecializacao()) || medico.getEspecializacao().isEmpty()) {
			throw new IllegalArgumentException("A especialização não pode estar em branco");
		}
		
		buscarEmailUsadoService.buscar(medico.getEmail());
		
		Criptografia criptografia = new Criptografia();
		medico.setSenha(criptografia.criptografarSenha(medico.getSenha()));
		
		if (Objects.isNull(medico.getEndereco())) {
			throw new IllegalArgumentException("O endereço não pode estar em branco");
		}
		
		cadastrarEndereco.salvar(medico.getEndereco());
		medicoRepository.save(medico);
	}
}

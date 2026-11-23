package com.brain.help.service.dependente;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.brain.help.entity.Dependente;
import com.brain.help.config.password.Criptografia;
import com.brain.help.repository.DiagnosticadoRepository;
import com.brain.help.service.endereco.CadastrarEnderecoService;
import com.brain.help.utils.ImageFileWriter;

@Service
public class CadastrarDiagnosticadoService {

	@Autowired
	DiagnosticadoRepository diagnosticadoRepository;

	@Autowired
	CadastrarEnderecoService cadastrarEndereco;

	
	@Autowired
	BuscarEmailDiagnosticadoUsadoService buscarEmailUsadoService;

	public void salvar(Dependente dependente) throws Exception {
		if (Objects.isNull(dependente.getNome()) || dependente.getNome().isEmpty()) {
			throw new IllegalArgumentException("O nome não pode estar em branco");
		}
		
		if (Objects.isNull(dependente.getSobrenome()) || dependente.getSobrenome().isEmpty()) {
			throw new IllegalArgumentException("O sobrenome não pode estar em branco");
		}
		
		if (Objects.isNull(dependente.getEmail()) || dependente.getEmail().isEmpty()) {
			throw new IllegalArgumentException("O email não pode estar em branco");
		}
		
		if (Objects.isNull(dependente.getTelefone()) || dependente.getTelefone().isEmpty()) {
			throw new IllegalArgumentException("O telefone não pode estar em branco");
		}
		
		if (Objects.isNull(dependente.getSenha()) || dependente.getSenha().isEmpty()) {
			throw new IllegalArgumentException("A senha não pode estar em branco");
		}
		
		if (Objects.isNull(dependente.getIdade())) {
			throw new IllegalArgumentException("A idade não pode estar em branco");
		}
		
		if (dependente.getIdade() > 110 || dependente.getIdade() < 20) {
			throw new IllegalArgumentException("A idade deve estar entre 20 e 110 anos");
		}
		
		if (Objects.isNull(dependente.getSexo())) {
			throw new IllegalArgumentException("O sexo não pode estar em branco");
		}
		
		if (Objects.isNull(dependente.getEstagioAlzheimer())) {
			throw new IllegalArgumentException("O estágio da doença não pode estar em branco");
		}
		
		if (Objects.isNull(dependente.getEndereco())) {
			throw new IllegalArgumentException("O endereço não pode estar em branco");
		}
		
		buscarEmailUsadoService.buscar(dependente.getEmail());
		
		if(!Objects.isNull(dependente.getFoto()) && !dependente.getFoto().isEmpty()) {
			String base64 = dependente.getFoto();
			dependente.setFoto(ImageFileWriter.saveImageToFolder(null, base64));
		}
		
		Criptografia criptografia = new Criptografia();
		dependente.setSenha(criptografia.criptografarSenha(dependente.getSenha()));
		
		cadastrarEndereco.salvar(dependente.getEndereco());
		diagnosticadoRepository.save(dependente);
	}
}
package com.example.demo.service.diagnosticado;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Diagnosticado;
import com.example.demo.model.security.password.Criptografia;
import com.example.demo.repository.DiagnosticadoRepository;
import com.example.demo.service.endereco.CadastrarEnderecoService;
import com.example.demo.utils.ImageFileWriter;

@Service
public class CadastrarDiagnosticadoService {

	@Autowired
	DiagnosticadoRepository diagnosticadoRepository;

	@Autowired
	CadastrarEnderecoService cadastrarEndereco;

	
	@Autowired
	BuscarEmailDiagnosticadoUsadoService buscarEmailUsadoService;

	public void salvar(Diagnosticado diagnosticado) throws Exception {
		if (Objects.isNull(diagnosticado.getNome()) || diagnosticado.getNome().isEmpty()) {
			throw new IllegalArgumentException("O nome não pode estar em branco");
		}
		
		if (Objects.isNull(diagnosticado.getSobrenome()) || diagnosticado.getSobrenome().isEmpty()) {
			throw new IllegalArgumentException("O sobrenome não pode estar em branco");
		}
		
		if (Objects.isNull(diagnosticado.getEmail()) || diagnosticado.getEmail().isEmpty()) {
			throw new IllegalArgumentException("O email não pode estar em branco");
		}
		
		if (Objects.isNull(diagnosticado.getTelefone()) || diagnosticado.getTelefone().isEmpty()) {
			throw new IllegalArgumentException("O telefone não pode estar em branco");
		}
		
		if (Objects.isNull(diagnosticado.getSenha()) || diagnosticado.getSenha().isEmpty()) {
			throw new IllegalArgumentException("A senha não pode estar em branco");
		}
		
		if (Objects.isNull(diagnosticado.getIdade())) {
			throw new IllegalArgumentException("A idade não pode estar em branco");
		}
		
		if (diagnosticado.getIdade() > 110 || diagnosticado.getIdade() < 20) {
			throw new IllegalArgumentException("A idade deve estar entre 20 e 110 anos");
		}
		
		if (Objects.isNull(diagnosticado.getSexo())) {
			throw new IllegalArgumentException("O sexo não pode estar em branco");
		}
		
		if (Objects.isNull(diagnosticado.getEstagioAlzheimer())) {
			throw new IllegalArgumentException("O estágio da doença não pode estar em branco");
		}
		
		if (Objects.isNull(diagnosticado.getEndereco())) {
			throw new IllegalArgumentException("O endereço não pode estar em branco");
		}
		
		if(!Objects.isNull(diagnosticado.getFoto()) && !diagnosticado.getFoto().isEmpty()) {
			String base64 = diagnosticado.getFoto();
			diagnosticado.setFoto(ImageFileWriter.saveImageToFolder(null, base64));
		}
		
		buscarEmailUsadoService.buscar(diagnosticado.getEmail());
		
		Criptografia criptografia = new Criptografia();
		diagnosticado.setSenha(criptografia.criptografarSenha(diagnosticado.getSenha()));
		
		cadastrarEndereco.salvar(diagnosticado.getEndereco());
		diagnosticadoRepository.save(diagnosticado);
	}
}
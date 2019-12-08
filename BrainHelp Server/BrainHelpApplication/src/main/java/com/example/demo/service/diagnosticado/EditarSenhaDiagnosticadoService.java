package com.example.demo.service.diagnosticado;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Diagnosticado;
import com.example.demo.model.dto.RedefinicaoSenhaRequestDto;
import com.example.demo.model.security.password.Criptografia;
import com.example.demo.repository.DiagnosticadoRepository;

@Service
public class EditarSenhaDiagnosticadoService {
	
	@Autowired
	BuscarDiagnosticadoPorEmailService buscarDiagnosticado;
	
	@Autowired
	DiagnosticadoRepository diagnosticadoRepository;
	
	public void editar(String emailDiagnosticado, RedefinicaoSenhaRequestDto redefinicaoSenha) {
		Diagnosticado diagnosticadoParaEditar = buscarDiagnosticado.buscar(emailDiagnosticado, false);
		
		if (Objects.isNull(redefinicaoSenha.getSenhaAtual()) || redefinicaoSenha.getSenhaAtual().isEmpty()) {
			throw new IllegalArgumentException("A senha atual não pode estar em branco");
		}
		
		if (Objects.isNull(redefinicaoSenha.getSenha()) || redefinicaoSenha.getSenha().isEmpty()) {
			throw new IllegalArgumentException("A nova senha não pode estar em branco");
		}
		
		
		Criptografia criptografia = new Criptografia();
		
		if(!criptografia.senhaIgual(redefinicaoSenha.getSenhaAtual(), diagnosticadoParaEditar.getSenha())) {
			throw new IllegalArgumentException("A senha atual está incorreta");
		}
		
		diagnosticadoParaEditar.setSenha(criptografia.criptografarSenha(redefinicaoSenha.getSenha()));
		diagnosticadoRepository.save(diagnosticadoParaEditar);
	}
}
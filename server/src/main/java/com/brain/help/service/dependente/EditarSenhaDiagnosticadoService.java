package com.brain.help.service.dependente;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.brain.help.entity.Dependente;
import com.brain.help.dto.senha.RedefinicaoSenhaRequestDto;
import com.brain.help.config.password.Criptografia;
import com.brain.help.repository.DependenteRepository;

@Service
public class EditarSenhaDiagnosticadoService {
	
	@Autowired
	BuscarDiagnosticadoPorEmailService buscarDiagnosticado;
	
	@Autowired
    DependenteRepository dependenteRepository;
	
	public void editar(String emailDiagnosticado, RedefinicaoSenhaRequestDto redefinicaoSenha) {
		Dependente dependenteParaEditar = buscarDiagnosticado.buscar(emailDiagnosticado, false);
		
		if (Objects.isNull(redefinicaoSenha.getSenhaAtual()) || redefinicaoSenha.getSenhaAtual().isEmpty()) {
			throw new IllegalArgumentException("A senha atual não pode estar em branco");
		}
		
		if (Objects.isNull(redefinicaoSenha.getSenha()) || redefinicaoSenha.getSenha().isEmpty()) {
			throw new IllegalArgumentException("A nova senha não pode estar em branco");
		}
		
		
		Criptografia criptografia = new Criptografia();
		
		if(!criptografia.senhaIgual(redefinicaoSenha.getSenhaAtual(), dependenteParaEditar.getSenha())) {
			throw new IllegalArgumentException("A senha atual está incorreta");
		}
		
		dependenteParaEditar.setSenha(criptografia.criptografarSenha(redefinicaoSenha.getSenha()));
		dependenteRepository.save(dependenteParaEditar);
	}
}
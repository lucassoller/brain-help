package com.brain.help.service.medico;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.brain.help.entity.Medico;
import com.brain.help.dto.senha.RedefinicaoSenhaRequestDto;
import com.brain.help.config.password.Criptografia;
import com.brain.help.repository.MedicoRepository;

@Service
public class EditarSenhaMedicoService {
	
	@Autowired
	MedicoRepository medicoRepository;

	@Autowired
	BuscarMedicoPorEmailService buscarMedico;

	public void editar(RedefinicaoSenhaRequestDto redefinicaoRequest) {
		if (Objects.isNull(redefinicaoRequest.getEmail()) || redefinicaoRequest.getEmail().isEmpty()) {
			throw new IllegalArgumentException("O email não pode estar em branco");
		}
		if (Objects.isNull(redefinicaoRequest.getSenha()) || redefinicaoRequest.getSenha().isEmpty()) {
			throw new IllegalArgumentException("A senha não pode estar em branco");
		}
		
		Medico medico = buscarMedico.buscar(redefinicaoRequest.getEmail(), false);
		
		Criptografia criptografia = new Criptografia();
		medico.setSenha(criptografia.criptografarSenha(redefinicaoRequest.getSenha()));
		medicoRepository.save(medico);
	}
}

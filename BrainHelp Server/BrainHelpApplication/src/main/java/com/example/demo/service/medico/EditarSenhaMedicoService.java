package com.example.demo.service.medico;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Medico;
import com.example.demo.model.dto.RedefinicaoSenhaRequestDto;
import com.example.demo.model.security.password.Criptografia;
import com.example.demo.repository.MedicoRepository;

@Service
public class EditarSenhaMedicoService {
	
	@Autowired
	MedicoRepository medicoRepository;

	@Autowired
	BuscarMedicoPorEmailService buscarMedico;

	public void editar(RedefinicaoSenhaRequestDto redefinicaoRequest) {
		if (Objects.isNull(redefinicaoRequest.getEmail()) || redefinicaoRequest.getEmail().isEmpty()) {
			throw new IllegalArgumentException("O email n�o pode estar em branco");
		}
		if (Objects.isNull(redefinicaoRequest.getSenha()) || redefinicaoRequest.getSenha().isEmpty()) {
			throw new IllegalArgumentException("A senha n�o pode estar em branco");
		}
		
		Medico medico = buscarMedico.buscar(redefinicaoRequest.getEmail());
		
		Criptografia criptografia = new Criptografia();
		medico.setSenha(criptografia.criptografarSenha(redefinicaoRequest.getSenha()));
		medicoRepository.save(medico);
	}
}

package com.brain.help.service.medico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.brain.help.dto.senha.RedefinicaoSenhaRequestDto;

@Service
public class EditarSenhaMedicoService {
	@Autowired
	MedicoService medicoService;

	public void editar(RedefinicaoSenhaRequestDto redefinicaoRequest) {
		medicoService.editar(redefinicaoRequest);
	}
}

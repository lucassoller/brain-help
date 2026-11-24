package com.brain.help.service.medico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.brain.help.dto.login.LoginRequestDto;
import com.brain.help.dto.login.LoginResponseDto;

@Service
public class LogarMedicoService {
	@Autowired
	MedicoService medicoService;

	public LoginResponseDto logar(LoginRequestDto loginDto) {
		return medicoService.logar(loginDto);
	}
}

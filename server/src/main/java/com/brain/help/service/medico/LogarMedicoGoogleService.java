package com.brain.help.service.medico;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.brain.help.entity.Medico;
import com.brain.help.dto.login.LoginRequestDto;
import com.brain.help.dto.login.LoginResponseDto;

@Service
public class LogarMedicoGoogleService {
	
	@Autowired
	private BuscarMedicoPorEmailEGoogleService buscarMedico;
	
    @Autowired
    AuthenticationService authenticationService;

	public LoginResponseDto logar(LoginRequestDto loginDto){
		
		if(Objects.isNull(loginDto.getIdentificacao()) || loginDto.getIdentificacao().isEmpty()){
			throw new IllegalArgumentException("O nome de usuário não pode estar em branco");
		}
		
		Medico medico = buscarMedico.buscar(loginDto.getIdentificacao(), true);
		medico.setSenha("senha");

		String token = authenticationService.authenticate(medico.getEmail(), medico.getSenha());
		return new LoginResponseDto(medico.getEmail(), token, medico.isGoogle());
	}
}

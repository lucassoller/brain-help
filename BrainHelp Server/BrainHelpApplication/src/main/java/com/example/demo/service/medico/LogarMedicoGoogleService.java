package com.example.demo.service.medico;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Medico;
import com.example.demo.model.dto.LoginRequestDto;
import com.example.demo.model.dto.LoginResponseDto;
import com.example.demo.model.security.AuthenticationService;

@Service
public class LogarMedicoGoogleService {
	
	@Autowired
	private BuscarMedicoPorEmailEGoogleService buscarMedico;
	
    @Autowired
    AuthenticationService authenticationService;

	public LoginResponseDto logar(LoginRequestDto loginDto){
		
		if(Objects.isNull(loginDto.getIdentificacao()) || loginDto.getIdentificacao().isEmpty()){
			throw new IllegalArgumentException("O nome de usu�rio n�o pode estar em branco");
		}
		
		Medico medico = buscarMedico.buscar(loginDto.getIdentificacao(), true);
		medico.setSenha("senha");

		String token = authenticationService.authenticate(medico.getEmail(), medico.getSenha());
		return new LoginResponseDto(medico.getEmail(), token);
	}
}

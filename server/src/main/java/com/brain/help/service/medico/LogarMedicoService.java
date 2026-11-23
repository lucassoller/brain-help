package com.brain.help.service.medico;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.brain.help.entity.Medico;
import com.brain.help.dto.login.LoginRequestDto;
import com.brain.help.dto.login.LoginResponseDto;
import com.brain.help.config.password.Criptografia;

@Service
public class LogarMedicoService {
	
	@Autowired
	private BuscarMedicoPorEmailService buscarMedico;
	
    @Autowired
    AuthenticationService authenticationService;

	public LoginResponseDto logar(LoginRequestDto loginDto){
		
		if(Objects.isNull(loginDto.getIdentificacao()) || loginDto.getIdentificacao().isEmpty()){
			throw new IllegalArgumentException("O nome de usuário não pode estar em branco");

		}
		if(Objects.isNull(loginDto.getSenha()) || loginDto.getSenha().isEmpty()){
			throw new IllegalArgumentException("A senha não pode estar em branco");

		}

		Criptografia criptografia = new Criptografia();
		Medico medico = buscarMedico.buscar(loginDto.getIdentificacao(), false);

		if(!criptografia.senhaIgual(loginDto.getSenha(), medico.getSenha())){
			throw new IllegalArgumentException("Senha incorreta");
		}

		String token = authenticationService.authenticate(medico.getEmail(), medico.getSenha());
		return new LoginResponseDto(medico.getEmail(), token, medico.isGoogle());
	}
}

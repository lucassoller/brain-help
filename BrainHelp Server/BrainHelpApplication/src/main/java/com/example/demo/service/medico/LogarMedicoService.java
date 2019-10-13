package com.example.demo.service.medico;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Medico;
import com.example.demo.model.dto.LoginRequestDto;
import com.example.demo.model.dto.LoginResponseDto;
import com.example.demo.model.security.AuthenticationService;
import com.example.demo.model.security.password.Criptografia;

@Service
public class LogarMedicoService {

	@Autowired
	private BuscarMedicoPorEmailOuIdService buscarMedico;
	
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
		Medico medico = buscarMedico.buscar(loginDto.getIdentificacao());

		if(!criptografia.senhaIgual(loginDto.getSenha(), medico.getSenha())){
			throw new IllegalArgumentException("Senha incorreta");
		}

		String token = authenticationService.authenticate(medico.getEmail(), medico.getSenha());
		return new LoginResponseDto(medico.getEmail(), token);
	}
}

package com.example.demo.service.diagnosticado;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Diagnosticado;
import com.example.demo.model.dto.LoginRequestDto;
import com.example.demo.model.dto.LoginResponseDto;
import com.example.demo.model.security.AuthenticationService;
import com.example.demo.model.security.password.Criptografia;

@Service
public class LogarDiagnosticadoService {

	@Autowired
	private BuscarDiagnosticadoPorEmailService buscarDiagnosticado;
	
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
		Diagnosticado diagnosticado = buscarDiagnosticado.buscar(loginDto.getIdentificacao(), false);

		if(!criptografia.senhaIgual(loginDto.getSenha(), diagnosticado.getSenha())){
			throw new IllegalArgumentException("Senha incorreta");
		}

		String token = authenticationService.authenticate(loginDto.getIdentificacao(), diagnosticado.getSenha());
		return new LoginResponseDto(diagnosticado.getEmail(), token, true);
	}
}

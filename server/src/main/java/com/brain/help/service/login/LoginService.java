package com.brain.help.service.login;

import com.brain.help.dto.login.LoginRequestDTO;
import com.brain.help.dto.login.LoginResponseDTO;
import com.brain.help.entity.Dependente;
import com.brain.help.entity.Medico;
import com.brain.help.exception.BusinessException;
import com.brain.help.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public LoginResponseDTO loginMedico(LoginRequestDTO dto) {
        Medico medico = autenticar(dto, Medico.class);
        return montarResponse(medico, medico.getId(), medico.getNome(), "ROLE_MEDICO");
    }

    public LoginResponseDTO loginDependente(LoginRequestDTO dto) {
        Dependente dependente = autenticar(dto, Dependente.class);
        return montarResponse(dependente, dependente.getId(), dependente.getNome(), "ROLE_DEPENDENTE");
    }

    private <T extends UserDetails> T autenticar(LoginRequestDTO dto, Class<T> tipoEsperado) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getSenha())
            );

            Object principal = auth.getPrincipal();
            if (!tipoEsperado.isInstance(principal)) {
                throw new BusinessException("E-mail ou senha inválidos");
            }

            return tipoEsperado.cast(principal);
        } catch (BadCredentialsException e) {
            throw new BusinessException("E-mail ou senha inválidos");
        }
    }

    private LoginResponseDTO montarResponse(UserDetails user, Long id, String nome, String role) {
        return LoginResponseDTO.builder()
                .token(jwtService.generateToken(user))
                .id(id)
                .nome(nome)
                .email(user.getUsername())
                .role(role)
                .build();
    }
}

package com.brain.help.security;

import com.brain.help.repository.DiagnosticadoRepository;
import com.brain.help.repository.MedicoRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomLogoutHandler implements LogoutHandler {

    private final MedicoRepository medicoRepository;
    private final DiagnosticadoRepository diagnosticadoRepository;

    @Override
    public void logout(HttpServletRequest request,
                       HttpServletResponse response,
                       Authentication authentication) {
        try {
            if (authentication == null || authentication.getName() == null) {
                return;
            }

            String email = authentication.getName();

            medicoRepository.findByEmail(email).ifPresentOrElse(
                    medico -> {
                        // Ação no logout do Médico (ex.: invalidar token, limpar chave/sessão ou registrar log)
                    },
                    () -> diagnosticadoRepository.findByEmail(email).ifPresent(diagnosticado -> {
                        // Ação no logout do Diagnosticado (ex.: invalidar token, limpar chave/sessão ou registrar log)
                    })
            );

        } catch (Exception ignored) {
        }
    }
}
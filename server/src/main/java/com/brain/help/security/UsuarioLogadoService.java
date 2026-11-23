package com.brain.help.security;

import com.brain.help.entity.Dependente;
import com.brain.help.entity.Medico;
import com.brain.help.repository.DiagnosticadoRepository;
import com.brain.help.repository.MedicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UsuarioLogadoService {

    private final DiagnosticadoRepository diagnosticadoRepository;
    private final MedicoRepository medicoRepository;

    public String getEmailLogado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication.getName() == null) {
            throw new UsernameNotFoundException("Nenhum usuário autenticado na sessão.");
        }
        return authentication.getName();
    }

    public Dependente getDiagnosticadoLogado() {
        String email = getEmailLogado();
        return diagnosticadoRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Diagnosticado não encontrado: " + email));
    }

    public Medico getMedicoLogado() {
        String email = getEmailLogado();
        return medicoRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Médico não encontrado: " + email));
    }
}
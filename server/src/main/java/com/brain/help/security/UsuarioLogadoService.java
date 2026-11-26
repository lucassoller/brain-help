package com.brain.help.security;

import com.brain.help.entity.Dependente;
import com.brain.help.entity.Medico;
import com.brain.help.repository.DependenteRepository;
import com.brain.help.repository.MedicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioLogadoService {

    private final DependenteRepository dependenteRepository;
    private final MedicoRepository medicoRepository;

    private Authentication getAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication.getName() == null) {
            throw new UsernameNotFoundException("Nenhum usuário autenticado na sessão.");
        }
        return authentication;
    }

    public String getEmailLogado() {
        return getAuthentication().getName();
    }

    public Dependente getDependenteLogado() {
        Authentication auth = getAuthentication();
        if (auth.getPrincipal() instanceof Dependente dependente) {
            return dependente;
        }
        return dependenteRepository.findByEmail(auth.getName())
                .orElseThrow(() -> new UsernameNotFoundException("Dependente não encontrado: " + auth.getName()));
    }


    public Long getDependenteIdLogado() {
        return this.getDependenteLogado().getId();
    }

    public Medico getMedicoLogado() {
        Authentication auth = getAuthentication();
        if (auth.getPrincipal() instanceof Medico medico) {
            return medico;
        }
        return medicoRepository.findByEmail(auth.getName())
                .orElseThrow(() -> new UsernameNotFoundException("Médico não encontrado: " + auth.getName()));
    }
}
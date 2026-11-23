package com.brain.help.security;

import com.brain.help.entity.Dependente;
import com.brain.help.entity.Medico;
import com.brain.help.repository.DiagnosticadoRepository;
import com.brain.help.repository.MedicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MedicoRepository medicoRepository;
    private final DiagnosticadoRepository diagnosticadoRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Medico> medicoOpt = medicoRepository.findByEmail(email);
        if (medicoOpt.isPresent()) {
            Medico medico = medicoOpt.get();
            return User
                    .withUsername(medico.getEmail())
                    .password(medico.getSenha())
                    .authorities(List.of(new SimpleGrantedAuthority("ROLE_MEDICO")))
                    .build();
        }

        Optional<Dependente> diagnosticadoOpt = diagnosticadoRepository.findByEmail(email);
        if (diagnosticadoOpt.isPresent()) {
            Dependente dependente = diagnosticadoOpt.get();
            return User
                    .withUsername(dependente.getEmail())
                    .password(dependente.getSenha())
                    .authorities(List.of(new SimpleGrantedAuthority("ROLE_DIAGNOSTICADO")))
                    .build();
        }

        throw new UsernameNotFoundException("Usuário não encontrado com o e-mail: " + email);
    }
}
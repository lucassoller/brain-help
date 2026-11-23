package com.brain.help.security;

import com.brain.help.repository.DependenteRepository;
import com.brain.help.repository.MedicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MedicoRepository medicoRepository;
    private final DependenteRepository dependenteRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return medicoRepository.findByEmail(email)
                .<UserDetails>map(medico -> medico)
                .or(() -> dependenteRepository.findByEmail(email))
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o e-mail: " + email));
    }
}
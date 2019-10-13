package com.example.demo.model.security;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import com.example.demo.model.Medico;
import com.example.demo.model.Usuario;
import com.example.demo.repository.DiagnosticadoRepository;
import com.example.demo.repository.MedicoRepository;

/*
* Service para obter dados do usuário no contexto de autenticação
*/
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    MedicoRepository medicoRepository;
    
    @Autowired
    DiagnosticadoRepository diagnosticadoRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) {
		Usuario usuario;
		Optional<Medico> medico = medicoRepository.findByEmail(email);
		if(medico.isPresent()) {
			usuario = medico.get();
		}else {
			usuario = diagnosticadoRepository.findByEmail(email)
					.orElseThrow(() -> new IllegalArgumentException("Nenhum usu�rio encontrado"));
		}		
        return UserPrincipal.create(usuario);
    }
}

package com.example.demo.service.medico;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Medico;
import com.example.demo.model.security.password.Criptografia;
import com.example.demo.repository.MedicoRepository;

@Service
public class EditarSenhaMedicoService {
	
	@Autowired
	MedicoRepository medicoRepository;

	@Autowired
	BuscarMedicoPorEmailService buscarMedico;

	public void editar(String email, String senha) {
		if (Objects.isNull(email) || email.isEmpty()) {
			throw new IllegalArgumentException("O email n�o pode estar em branco");
		}
		if (Objects.isNull(senha) || senha.isEmpty()) {
			throw new IllegalArgumentException("A senha n�o pode estar em branco");
		}
		
		Medico medico = buscarMedico.buscar(email);
		
		Criptografia criptografia = new Criptografia();
		medico.setSenha(criptografia.criptografarSenha(senha));
		medicoRepository.save(medico);
	}
}

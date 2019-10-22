package com.example.demo.service.email;

import java.util.Date;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.email.Email;
import com.example.demo.model.Medico;
import com.example.demo.repository.MedicoRepository;
import com.example.demo.service.medico.BuscarMedicoPorEmailService;

@Service
public class RedefinirSenhaService {
	
	@Autowired
	BuscarMedicoPorEmailService buscarMedico;
	
	@Autowired
	MedicoRepository medicoRepository;
	
	public Medico redefinir(String emailRecuperacao) {
		if(Objects.isNull(emailRecuperacao) || emailRecuperacao.isEmpty()) {
			throw new IllegalArgumentException("O e-mail não pode estar em branco");
		}
		
		Medico medico = buscarMedico.buscar(emailRecuperacao);
		String url = medico.getUrlRedefinicao().split("#")[1];
		
		Date date = new Date();
		return medico;
	}
}
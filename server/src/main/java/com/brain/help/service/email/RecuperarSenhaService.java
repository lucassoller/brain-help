package com.brain.help.service.email;

import java.util.Date;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.brain.help.email.Email;
import com.brain.help.repository.MedicoRepository;
import com.brain.help.service.medico.BuscarMedicoPorEmailEGoogleService;

@Service
public class RecuperarSenhaService {
	
	@Autowired
	BuscarMedicoPorEmailEGoogleService buscarMedico;
	
	@Autowired
	MedicoRepository medicoRepository;
	
	public void recuperar(String emailRecuperacao) {
		if(Objects.isNull(emailRecuperacao) || emailRecuperacao.isEmpty()) {
			throw new IllegalArgumentException("O e-mail não pode estar em branco");
		}
		
		buscarMedico.buscar(emailRecuperacao, false);
		long data = new Date().getTime();
		
		String url = emailRecuperacao + ":" + data;
		
		new Email().enviarEmail(emailRecuperacao, url);
	}
}
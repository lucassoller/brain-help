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
public class RecuperarSenhaService {
	
	@Autowired
	BuscarMedicoPorEmailService buscarMedico;
	
	@Autowired
	MedicoRepository medicoRepository;
	
	public void recuperar(String emailRecuperacao) {
		if(Objects.isNull(emailRecuperacao) || emailRecuperacao.isEmpty()) {
			throw new IllegalArgumentException("O e-mail não pode estar em branco");
		}
		
		Medico medico = buscarMedico.buscar(emailRecuperacao);
		long data = new Date().getTime();
		
		String url = emailRecuperacao + "#" + data;
		
		new Email().enviarEmail(emailRecuperacao, url);
		
		medico.setUrlRedefinicao(url);
		medicoRepository.save(medico);
	}
}
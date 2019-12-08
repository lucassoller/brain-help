package com.example.demo.service.email;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.repository.MedicoRepository;
import com.example.demo.service.medico.BuscarMedicoPorEmailService;

@Service
public class RedefinirSenhaService {
	
	@Autowired
	BuscarMedicoPorEmailService buscarMedico;
	
	@Autowired
	MedicoRepository medicoRepository;
	
	public void redefinir(String url) {
		if(Objects.isNull(url) || url.isEmpty()) {
			throw new IllegalArgumentException("A URL não pode estar em branco");
		}
		
		String emailRecuperacao = url.split(":")[0];
		long dataUrl = Long.parseLong(url.split(":")[1]);
		
		buscarMedico.buscar(emailRecuperacao, false);
		
		GregorianCalendar gc = new GregorianCalendar();
		
		gc.setTimeInMillis(dataUrl);
		gc.add(Calendar.MINUTE, 30);
		
		if(new Date().getTime() > gc.getTimeInMillis()) {
			throw new IllegalArgumentException("Esse link já expirou");
		}
	}
}
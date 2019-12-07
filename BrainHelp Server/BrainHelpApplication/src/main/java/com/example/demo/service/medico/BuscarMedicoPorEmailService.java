package com.example.demo.service.medico;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Medico;
import com.example.demo.repository.MedicoRepository;
import com.example.demo.utils.ImageFileWriter;

@Service
public class BuscarMedicoPorEmailService {

	@Autowired
	MedicoRepository medicoRepository;

	public Medico buscar(String emailMedico) {
		if ((Objects.isNull(emailMedico) || emailMedico.isEmpty())) {
			throw new IllegalArgumentException("O email não pode estar em branco");
		}		
		
		Medico medico = medicoRepository.findByEmail(emailMedico)
				.orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
		
		if(!Objects.isNull(medico.getFoto()) && !medico.getFoto().isEmpty()) {
			try {
				medico.setFoto(ImageFileWriter.returnBase64FromFile(medico.getFoto()));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return medico;
	}
}
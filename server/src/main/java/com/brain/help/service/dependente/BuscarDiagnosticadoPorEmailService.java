package com.brain.help.service.dependente;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.brain.help.entity.Dependente;
import com.brain.help.repository.DependenteRepository;
import com.brain.help.utils.ImageFileWriter;

@Service
public class BuscarDiagnosticadoPorEmailService {

	@Autowired
    DependenteRepository dependenteRepository;

	public Dependente buscar(String email, boolean foto) {
		if ((Objects.isNull(email) || email.isEmpty())) {
			throw new IllegalArgumentException("O email não pode estar em branco");
		}
	
		Dependente dependente = dependenteRepository.findByEmail(email)
				.orElseThrow(() -> new IllegalArgumentException("Nenhum diagnosticado foi encontrado"));
		
		if(foto && !Objects.isNull(dependente.getFoto()) && !dependente.getFoto().isEmpty()) {
			try {
				dependente.setFoto(ImageFileWriter.returnBase64FromFile(dependente.getFoto()));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return dependente;
	}
}

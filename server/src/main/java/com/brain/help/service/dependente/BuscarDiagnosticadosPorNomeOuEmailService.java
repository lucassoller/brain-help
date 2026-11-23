package com.brain.help.service.dependente;

import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.brain.help.entity.Dependente;
import com.brain.help.repository.DependenteRepository;
import com.brain.help.utils.ImageFileWriter;

@Service
public class BuscarDiagnosticadosPorNomeOuEmailService {

	@Autowired
    DependenteRepository dependenteRepository;

	public List<Dependente> buscar(String email, String nome, boolean medicoPresente) {
		if ((Objects.isNull(email) || email.isEmpty())) {
			throw new IllegalArgumentException("O email não pode estar em branco");
		}
		
		if ((Objects.isNull(nome) || nome.isEmpty())) {
			throw new IllegalArgumentException("O nome não pode estar em branco");
		}
		
		List<Dependente> dependentes;
		
		if(medicoPresente) {
			dependentes = dependenteRepository.findByMedico(email, nome);
		}else {
			dependentes = dependenteRepository.findByMedicoNotLike(nome);
		}
		
		dependentes.stream().forEach(diagnosticado ->{
			try {
				if(!Objects.isNull(diagnosticado.getFoto()) && !diagnosticado.getFoto().isEmpty()) {
					diagnosticado.setFoto(ImageFileWriter.returnBase64FromFile(diagnosticado.getFoto()));
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		return dependentes;
	}
}

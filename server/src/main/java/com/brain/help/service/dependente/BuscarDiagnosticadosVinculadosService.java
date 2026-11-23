package com.brain.help.service.dependente;

import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.brain.help.entity.Dependente;
import com.brain.help.repository.DiagnosticadoRepository;
import com.brain.help.utils.ImageFileWriter;

@Service
public class BuscarDiagnosticadosVinculadosService {

	@Autowired
	DiagnosticadoRepository diagnosticadoRepository;

	public List<Dependente> buscar(String emailMedico) {
		if ((Objects.isNull(emailMedico) || emailMedico.isEmpty())) {
			throw new IllegalArgumentException("O email não pode estar em branco");
		}
		
		List<Dependente> dependentes = diagnosticadoRepository.findAllByMedico(emailMedico);

		
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

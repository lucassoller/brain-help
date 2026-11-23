package com.brain.help.service.fotografia;

import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.brain.help.entity.Dependente;
import com.brain.help.entity.Fotografia;
import com.brain.help.repository.FotografiaRepository;
import com.brain.help.service.dependente.BuscarDiagnosticadoPorEmailService;
import com.brain.help.utils.ImageFileWriter;

@Service
public class BuscarFotografiasDoUsuarioService {

	@Autowired
	FotografiaRepository fotografiaRepository;
	
	@Autowired
	BuscarDiagnosticadoPorEmailService buscarDiagnosticado;

	public List<Fotografia> buscar(String emailDiagnosticado) {
		if (Objects.isNull(emailDiagnosticado) || emailDiagnosticado.isEmpty()) {
			throw new IllegalArgumentException("O email do diagnosticado não pode estar em branco");
		}
		Dependente dependente = buscarDiagnosticado.buscar(emailDiagnosticado, false);
		List<Fotografia> fotografias = fotografiaRepository.findByDiagnosticado(dependente);
		fotografias.stream().forEach(fotografia ->{
			try {
				if(!Objects.isNull(fotografia.getFoto()) && !fotografia.getFoto().isEmpty()) {
					fotografia.setFoto(ImageFileWriter.returnBase64FromFile(fotografia.getFoto()));
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		return fotografias;
	}
}
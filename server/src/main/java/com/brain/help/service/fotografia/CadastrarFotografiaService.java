package com.brain.help.service.fotografia;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.brain.help.entity.Dependente;
import com.brain.help.entity.Fotografia;
import com.brain.help.repository.FotografiaRepository;
import com.brain.help.utils.ImageFileWriter;

@Service
public class CadastrarFotografiaService {
	
	@Autowired
	FotografiaRepository fotografiaRepository;
	
	@Autowired
	BuscarDiagnosticadoPorEmailService buscarDiagnosticado;
	

	public void salvar(String emailDiagnosticado, Fotografia fotografia) throws Exception {
		if (Objects.isNull(fotografia.getLugar()) || fotografia.getLugar().isEmpty()) {
			throw new IllegalArgumentException("O lugar não pode estar em branco");
		}
		
		if (Objects.isNull(fotografia.getLembrancas()) || fotografia.getLembrancas().isEmpty()) {
			throw new IllegalArgumentException("As lembranças da fotografia não podem estar em branco");
		}
		
		if(!Objects.isNull(fotografia.getFoto()) && !fotografia.getFoto().isEmpty()) {
			String base64 = fotografia.getFoto();
			fotografia.setFoto(ImageFileWriter.saveImageToFolder(null, base64));
		}
		Dependente dependente = buscarDiagnosticado.buscar(emailDiagnosticado, false);
		
		fotografia.setDependente(dependente);
		fotografiaRepository.save(fotografia);
	}
}
package com.example.demo.service.fotografia;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Diagnosticado;
import com.example.demo.model.Fotografia;
import com.example.demo.repository.FotografiaRepository;
import com.example.demo.service.diagnosticado.BuscarDiagnosticadoPorEmailService;
import com.example.demo.utils.ImageFileWriter;

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
		Diagnosticado diagnosticado = buscarDiagnosticado.buscar(emailDiagnosticado, false);
		
		fotografia.setDiagnosticado(diagnosticado);
		fotografiaRepository.save(fotografia);
	}
}
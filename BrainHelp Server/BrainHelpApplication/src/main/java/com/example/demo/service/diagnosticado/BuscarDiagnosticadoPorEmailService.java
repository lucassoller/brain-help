package com.example.demo.service.diagnosticado;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Diagnosticado;
import com.example.demo.repository.DiagnosticadoRepository;
import com.example.demo.utils.ImageFileWriter;

@Service
public class BuscarDiagnosticadoPorEmailService {

	@Autowired
	DiagnosticadoRepository diagnosticadoRepository;

	public Diagnosticado buscar(String email, boolean foto) {
		if ((Objects.isNull(email) || email.isEmpty())) {
			throw new IllegalArgumentException("O email não pode estar em branco");
		}
	
		Diagnosticado diagnosticado = diagnosticadoRepository.findByEmail(email)
				.orElseThrow(() -> new IllegalArgumentException("Nenhum diagnosticado foi encontrado"));
		
		if(foto && !Objects.isNull(diagnosticado.getFoto()) && !diagnosticado.getFoto().isEmpty()) {
			try {
				diagnosticado.setFoto(ImageFileWriter.returnBase64FromFile(diagnosticado.getFoto()));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return diagnosticado;
	}
}

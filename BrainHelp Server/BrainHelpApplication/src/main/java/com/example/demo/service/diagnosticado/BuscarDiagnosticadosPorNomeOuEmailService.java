package com.example.demo.service.diagnosticado;

import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Diagnosticado;
import com.example.demo.repository.DiagnosticadoRepository;
import com.example.demo.utils.ImageFileWriter;

@Service
public class BuscarDiagnosticadosPorNomeOuEmailService {

	@Autowired
	DiagnosticadoRepository diagnosticadoRepository;

	public List<Diagnosticado> buscar(String email, String nome, boolean medicoPresente) {
		if ((Objects.isNull(email) || email.isEmpty())) {
			throw new IllegalArgumentException("O email não pode estar em branco");
		}
		
		if ((Objects.isNull(nome) || nome.isEmpty())) {
			throw new IllegalArgumentException("O nome não pode estar em branco");
		}
		
		List<Diagnosticado> diagnosticados;
		
		if(medicoPresente) {
			diagnosticados = diagnosticadoRepository.findByMedico(email, nome);
		}else {
			diagnosticados = diagnosticadoRepository.findByMedicoNotLike(nome);
		}
		
		diagnosticados.stream().forEach(diagnosticado ->{
			try {
				if(!Objects.isNull(diagnosticado.getFoto()) && !diagnosticado.getFoto().isEmpty()) {
					diagnosticado.setFoto(ImageFileWriter.returnBase64FromFile(diagnosticado.getFoto()));
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		return diagnosticados;
	}
}

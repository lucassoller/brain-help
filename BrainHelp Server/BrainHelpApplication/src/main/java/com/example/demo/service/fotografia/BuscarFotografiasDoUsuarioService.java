package com.example.demo.service.fotografia;

import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Diagnosticado;
import com.example.demo.model.Fotografia;
import com.example.demo.repository.FotografiaRepository;
import com.example.demo.service.diagnosticado.BuscarDiagnosticadoPorEmailService;
import com.example.demo.utils.ImageFileWriter;

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
		Diagnosticado diagnosticado = buscarDiagnosticado.buscar(emailDiagnosticado, false);
		List<Fotografia> fotografias = fotografiaRepository.findByDiagnosticado(diagnosticado);
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
package com.example.demo.service.vinculo;

import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Diagnosticado;
import com.example.demo.model.Vinculo;
import com.example.demo.repository.VinculoRepository;
import com.example.demo.service.diagnosticado.BuscarDiagnosticadoPorEmailService;
import com.example.demo.utils.ImageFileWriter;

@Service
public class BuscarVinculosDoUsuarioService {

	@Autowired
	VinculoRepository vinculoRepository;
	
	@Autowired
	BuscarDiagnosticadoPorEmailService buscarDiagnosticado;

	public List<Vinculo> buscar(String emailDiagnosticado) {
		if (Objects.isNull(emailDiagnosticado) || emailDiagnosticado.isEmpty()) {
			throw new IllegalArgumentException("O email do diagnosticado não pode estar em branco");
		}
		Diagnosticado diagnosticado = buscarDiagnosticado.buscar(emailDiagnosticado, false);
		List<Vinculo> vinculos = vinculoRepository.findByDiagnosticado(diagnosticado);
		vinculos.stream().forEach(vinculo ->{
			try {
				if(!Objects.isNull(vinculo.getFoto()) && !vinculo.getFoto().isEmpty()) {
					vinculo.setFoto(ImageFileWriter.returnBase64FromFile(vinculo.getFoto()));
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		return vinculos;
	}
}
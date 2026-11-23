package com.brain.help.service.vinculo;

import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brain.help.entity.Dependente;
import com.brain.help.entity.Vinculo;
import com.brain.help.repository.VinculoRepository;
import com.brain.help.service.dependente.BuscarDiagnosticadoPorEmailService;
import com.brain.help.utils.ImageFileWriter;

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
		Dependente dependente = buscarDiagnosticado.buscar(emailDiagnosticado, false);
		List<Vinculo> vinculos = vinculoRepository.findByDiagnosticado(dependente);
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
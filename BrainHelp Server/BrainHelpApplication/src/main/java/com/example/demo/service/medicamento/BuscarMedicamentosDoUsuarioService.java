package com.example.demo.service.medicamento;

import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Diagnosticado;
import com.example.demo.model.Medicamento;
import com.example.demo.repository.MedicamentoRepository;
import com.example.demo.service.diagnosticado.BuscarDiagnosticadoPorEmailService;
import com.example.demo.utils.ImageFileWriter;

@Service
public class BuscarMedicamentosDoUsuarioService {

	@Autowired
	MedicamentoRepository medicamentoRepository;
	
	@Autowired
	BuscarDiagnosticadoPorEmailService buscarDiagnosticado;

	public List<Medicamento> buscar(String emailDiagnosticado) {
		if (Objects.isNull(emailDiagnosticado) || emailDiagnosticado.isEmpty()) {
			throw new IllegalArgumentException("O email do diagnosticado não pode estar em branco");
		}
		Diagnosticado diagnosticado = buscarDiagnosticado.buscar(emailDiagnosticado, false);
		List<Medicamento> medicamentos = medicamentoRepository.findByDiagnosticado(diagnosticado);
		medicamentos.stream().forEach(medicamento ->{
			try {
				if(!Objects.isNull(medicamento.getFoto()) && !medicamento.getFoto().isEmpty()) {
					medicamento.setFoto(ImageFileWriter.returnBase64FromFile(medicamento.getFoto()));
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		return medicamentos;
	}
}
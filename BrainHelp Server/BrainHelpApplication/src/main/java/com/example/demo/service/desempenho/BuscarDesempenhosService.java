package com.example.demo.service.desempenho;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Desempenho;
import com.example.demo.model.Diagnosticado;
import com.example.demo.repository.DesempenhoRepository;
import com.example.demo.service.diagnosticado.BuscarDiagnosticadoPorEmailService;

@Service
public class BuscarDesempenhosService {

	@Autowired
	DesempenhoRepository desempenhoRepository;
	
	@Autowired
	BuscarDiagnosticadoPorEmailService buscarDiagnosticado;
	public List<Desempenho> buscar(String emailDiagnosticado, Date dataInicial, Date dataFinal){
		Diagnosticado diagnosticado = buscarDiagnosticado.buscar(emailDiagnosticado);
		return desempenhoRepository.findByDiagnosticadoAndDataRealizacaoBetween(diagnosticado, dataInicial, dataFinal);
	}
}

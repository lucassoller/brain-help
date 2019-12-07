package com.example.demo.service.desempenho;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	public List<Desempenho> buscar(String emailDiagnosticado, String dataInicial, String dataFinal) throws ParseException{
		Diagnosticado diagnosticado = buscarDiagnosticado.buscar(emailDiagnosticado);
		
		SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date dataI = dataFormat.parse(dataInicial);
		Date dataF = dataFormat.parse(dataFinal);
		
		return desempenhoRepository.findByDiagnosticadoAndDataRealizacaoBetween(diagnosticado, dataI, dataF);
	}
}

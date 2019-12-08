package com.example.demo.service.endereco;

import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Diagnosticado;
import com.example.demo.model.Endereco;
import com.example.demo.repository.EnderecoRepository;
import com.example.demo.service.diagnosticado.BuscarDiagnosticadoPorEmailService;
import com.example.demo.utils.ImageFileWriter;

@Service
public class BuscarEnderecosDoUsuarioService {

	@Autowired
	EnderecoRepository enderecoRepository;
	
	@Autowired
	BuscarDiagnosticadoPorEmailService buscarDiagnosticado;

	public List<Endereco> buscar(String emailDiagnosticado) {
		if (Objects.isNull(emailDiagnosticado) || emailDiagnosticado.isEmpty()) {
			throw new IllegalArgumentException("O email do diagnosticado não pode estar em branco");
		}
		Diagnosticado diagnosticado = buscarDiagnosticado.buscar(emailDiagnosticado, false);
		List<Endereco> enderecos = enderecoRepository.findByDiagnosticado(diagnosticado);
		enderecos.stream().forEach(endereco ->{
			try {
				if(!Objects.isNull(endereco.getFoto()) && !endereco.getFoto().isEmpty()) {
					endereco.setFoto(ImageFileWriter.returnBase64FromFile(endereco.getFoto()));
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		return enderecos;
	}
}
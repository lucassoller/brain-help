package com.brain.help.service.endereco;

import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.brain.help.entity.Dependente;
import com.brain.help.entity.Endereco;
import com.brain.help.repository.EnderecoRepository;
import com.brain.help.utils.ImageFileWriter;

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
		Dependente dependente = buscarDiagnosticado.buscar(emailDiagnosticado, false);
		List<Endereco> enderecos = enderecoRepository.findByDiagnosticado(dependente);
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
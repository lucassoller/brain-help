package com.example.demo.service.endereco;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Diagnosticado;
import com.example.demo.model.Endereco;
import com.example.demo.repository.EnderecoRepository;
import com.example.demo.service.diagnosticado.BuscarDiagnosticadoPorEmailService;
import com.example.demo.utils.ImageFileWriter;

@Service
public class CadastrarListaEnderecoService {

	@Autowired
	EnderecoRepository enderecoRepository;
	
	@Autowired
	BuscarDiagnosticadoPorEmailService buscarDiagnosticado;

	public void salvar(Endereco endereco, String email) throws Exception {
		if (Objects.isNull(endereco.getLogradouro()) || endereco.getLogradouro().isEmpty()) {
			throw new IllegalArgumentException("O logadouro não pode estar em branco");
		}
		
		if (Objects.isNull(endereco.getNumero()) || endereco.getNumero() <= 0) {
			throw new IllegalArgumentException("O número não pode estar em branco");
		}
		
		if (Objects.isNull(endereco.getCidade()) || endereco.getCidade().isEmpty()) {
			throw new IllegalArgumentException("A cidade não pode estar em branco");
		}
		
		if (Objects.isNull(endereco.getEstado()) || endereco.getEstado().isEmpty()) {
			throw new IllegalArgumentException("O estado não pode estar em branco");
		}
		
		if (Objects.isNull(endereco.getBairro()) || endereco.getBairro().isEmpty()) {
			throw new IllegalArgumentException("O bairro não pode estar em branco");
		}
		
		if (Objects.isNull(endereco.getCep()) || endereco.getCep().isEmpty()) {
			throw new IllegalArgumentException("O CEP não pode estar em branco");
		}
		
		if(!Objects.isNull(endereco.getFoto()) && !endereco.getFoto().isEmpty()) {
			String base64 = endereco.getFoto();
			endereco.setFoto(ImageFileWriter.saveImageToFolder(null, base64));
		}
		
		Diagnosticado diagnosticado = buscarDiagnosticado.buscar(email, false);
		
		endereco.setDiagnosticado(diagnosticado);
		enderecoRepository.save(endereco);
	}
}

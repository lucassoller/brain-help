package com.example.demo.service.endereco;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Endereco;
import com.example.demo.repository.EnderecoRepository;

@Service
public class BuscarEnderecoPorIdService {

	@Autowired
	EnderecoRepository enderecoRepository;

	public Endereco buscar(Integer codEndereco) {
		if ((Objects.isNull(codEndereco))) {
			throw new IllegalArgumentException("O c�digo do endere�o n�o pode estar em branco");
		}
		
		return enderecoRepository.findById(codEndereco)
				.orElseThrow(() -> new IllegalArgumentException("Nenhum endere�o foi encontrado"));
	}
}

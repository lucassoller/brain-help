package com.brain.help.service.endereco;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.brain.help.entity.Endereco;
import com.brain.help.repository.EnderecoRepository;

@Service
public class BuscarEnderecoPorIdService {

	@Autowired
	EnderecoRepository enderecoRepository;

	public Endereco buscar(Integer codEndereco) {
		if ((Objects.isNull(codEndereco))) {
			throw new IllegalArgumentException("O código do endereço não pode estar em branco");
		}
		
		return enderecoRepository.findById(codEndereco)
				.orElseThrow(() -> new IllegalArgumentException("Nenhum endereço foi encontrado"));
	}
}

package com.example.demo.service.endereco;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Endereco;
import com.example.demo.repository.EnderecoRepository;

@Service
public class CadastrarEnderecoService {

	@Autowired
	EnderecoRepository enderecoRepository;

	public void salvar(Endereco endereco) {
		if (Objects.isNull(endereco.getEndereco()) || endereco.getEndereco().isEmpty()) {
			throw new IllegalArgumentException("O endereço não pode estar em branco");
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
		
		if (Objects.isNull(endereco.getPais()) || endereco.getPais().isEmpty()) {
			throw new IllegalArgumentException("O país não pode estar em branco");
		}
		
		if (Objects.isNull(endereco.getBairro()) || endereco.getBairro().isEmpty()) {
			throw new IllegalArgumentException("O bairro não pode estar em branco");
		}
		
		if (Objects.isNull(endereco.getCep()) || endereco.getCep().isEmpty()) {
			throw new IllegalArgumentException("O CEP não pode estar em branco");
		}
		
		enderecoRepository.save(endereco);
	}
}

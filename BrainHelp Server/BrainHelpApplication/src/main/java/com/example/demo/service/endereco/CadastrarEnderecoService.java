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
		
		enderecoRepository.save(endereco);
	}
}

package com.example.demo.service.endereco;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Endereco;
import com.example.demo.repository.EnderecoRepository;

@Service
public class EditarEnderecoService {

	@Autowired
	EnderecoRepository enderecoRepository;

	public void editar(Endereco enderecoParaEditar, Endereco endereco) {
		if (!Objects.isNull(endereco.getLogradouro()) && !endereco.getLogradouro().isEmpty()) {
			enderecoParaEditar.setLogradouro(endereco.getLogradouro());
		}
		
		if (!Objects.isNull(endereco.getNumero()) && endereco.getNumero() > 0) {
			enderecoParaEditar.setNumero(endereco.getNumero());
		}
		
		if (!Objects.isNull(endereco.getCidade()) && !endereco.getCidade().isEmpty()) {
			enderecoParaEditar.setCidade(endereco.getCidade());
		}
		
		if (!Objects.isNull(endereco.getEstado()) && !endereco.getEstado().isEmpty()) {
			enderecoParaEditar.setEstado(endereco.getEstado());
		}
		
		if (!Objects.isNull(endereco.getBairro()) && !endereco.getBairro().isEmpty()) {
			enderecoParaEditar.setBairro(endereco.getBairro());
		}
		
		if (!Objects.isNull(endereco.getCep()) && !endereco.getCep().isEmpty()) {
			enderecoParaEditar.setCep(endereco.getCep());
		}
		
		enderecoRepository.save(enderecoParaEditar);
	}
}

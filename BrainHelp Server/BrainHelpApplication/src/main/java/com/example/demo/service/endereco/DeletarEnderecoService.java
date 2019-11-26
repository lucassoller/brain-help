package com.example.demo.service.endereco;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Endereco;
import com.example.demo.repository.EnderecoRepository;

@Service
public class DeletarEnderecoService {

	@Autowired
	BuscarEnderecoPorIdService buscarEndereco;
	
	@Autowired
	EnderecoRepository enderecoRepository;
	
	public void deletar(Integer codEndereco) {
		Endereco endereco = buscarEndereco.buscar(codEndereco);
		enderecoRepository.delete(endereco);
	}
}

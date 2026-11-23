package com.brain.help.service.endereco;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.brain.help.entity.Endereco;
import com.brain.help.repository.EnderecoRepository;

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

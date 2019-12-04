package com.example.demo.service.endereco;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Endereco;
import com.example.demo.repository.EnderecoRepository;
import com.example.demo.utils.ImageFileWriter;

@Service
public class EditarEnderecoService {

	@Autowired
	EnderecoRepository enderecoRepository;
	
	@Autowired
	BuscarEnderecoPorIdService buscarEndereco;

	public void editar(Endereco endereco) throws Exception {
		Endereco enderecoParaEditar = buscarEndereco.buscar(endereco.getCodEndereco());
		if (!Objects.isNull(endereco.getTitulo()) && !endereco.getTitulo().isEmpty()) {
			enderecoParaEditar.setTitulo(endereco.getTitulo());
		}
		
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
		
		if (!Objects.isNull(endereco.getFoto()) && !endereco.getFoto().isEmpty()) {
			if(!enderecoParaEditar.getFoto().isEmpty()) {
				ImageFileWriter.deleteFile(enderecoParaEditar.getFoto());
			}
			String base64 = endereco.getFoto();
			enderecoParaEditar.setFoto(ImageFileWriter.saveImageToFolder(null, base64));
		}
		
		enderecoRepository.save(enderecoParaEditar);
	}
}
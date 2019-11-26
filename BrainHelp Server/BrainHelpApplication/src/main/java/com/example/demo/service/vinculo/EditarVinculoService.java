package com.example.demo.service.vinculo;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Vinculo;
import com.example.demo.repository.VinculoRepository;
import com.example.demo.service.endereco.EditarEnderecoService;

@Service
public class EditarVinculoService {
	
	@Autowired
	BuscarVinculoPorIdService buscarVinculo;
	
	@Autowired
	EditarEnderecoService editarEndereco;
	
	@Autowired
	VinculoRepository vinculoRepository;
	
	public void editar(Integer codVinculo, Vinculo vinculo) {
		Vinculo vinculoParaEditar = buscarVinculo.buscar(codVinculo);
		
		if (!Objects.isNull(vinculo.getNome()) && !vinculo.getNome().isEmpty()) {
			vinculoParaEditar.setNome(vinculo.getNome());
		}
		
		if (!Objects.isNull(vinculo.getSobrenome()) && !vinculo.getSobrenome().isEmpty()) {
			vinculoParaEditar.setSobrenome(vinculo.getSobrenome());
		}
		
		if (!Objects.isNull(vinculo.getTelefone()) && !vinculo.getTelefone().isEmpty()) {
			vinculoParaEditar.setTelefone(vinculo.getTelefone());
		}
		
		if (!Objects.isNull(vinculo.getVinculo()) && !vinculo.getVinculo().isEmpty()) {
			vinculoParaEditar.setVinculo(vinculo.getVinculo());
		}
		
		if (!Objects.isNull(vinculo.getLembrancas()) && !vinculo.getLembrancas().isEmpty()) {
			vinculoParaEditar.setLembrancas(vinculo.getLembrancas());
		}
		
		if (!Objects.isNull(vinculo.getFoto()) && !vinculo.getFoto().isEmpty()) {
			vinculoParaEditar.setFoto(vinculo.getFoto());
		}
		
		if (!Objects.isNull(vinculo.getSexo())) {
			vinculoParaEditar.setSexo(vinculo.getSexo());
		}
		
		if (!Objects.isNull(vinculo.getIdade())) {
			vinculoParaEditar.setIdade(vinculo.getIdade());
		}
		
		if (!Objects.isNull(vinculo.getEndereco())) {
			editarEndereco.editar(vinculoParaEditar.getEndereco(), vinculo.getEndereco());		
		}
		
		vinculoRepository.save(vinculoParaEditar);
	}
}
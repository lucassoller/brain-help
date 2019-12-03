package com.example.demo.service.vinculo;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Vinculo;
import com.example.demo.repository.VinculoRepository;
import com.example.demo.service.endereco.EditarEnderecoService;
import com.example.demo.utils.ImageFileWriter;

@Service
public class EditarVinculoService {
	
	@Autowired
	BuscarVinculoPorIdService buscarVinculo;
	
	@Autowired
	EditarEnderecoService editarEndereco;
	
	@Autowired
	VinculoRepository vinculoRepository;
	
	public void editar(Vinculo vinculo) throws Exception {
		Vinculo vinculoParaEditar = buscarVinculo.buscar(vinculo.getCodVinculo());
		
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
			if(!vinculoParaEditar.getFoto().isEmpty()) {
				ImageFileWriter.deleteFile(vinculoParaEditar.getFoto());
			}
			String base64 = vinculo.getFoto();
			vinculoParaEditar.setFoto(ImageFileWriter.saveImageToFolder(null, base64));
		}
		
		if (!Objects.isNull(vinculo.getSexo())) {
			vinculoParaEditar.setSexo(vinculo.getSexo());
		}
		
		if (!Objects.isNull(vinculo.getIdade())) {
			vinculoParaEditar.setIdade(vinculo.getIdade());
		}
		
		if (!Objects.isNull(vinculo.getEndereco())) {
			editarEndereco.editar(vinculo.getEndereco());		
		}
		
		vinculoRepository.save(vinculoParaEditar);
	}
}
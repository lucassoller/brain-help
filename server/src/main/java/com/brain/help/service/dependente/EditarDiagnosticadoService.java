package com.brain.help.service.dependente;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.brain.help.entity.Dependente;
import com.brain.help.repository.DiagnosticadoRepository;
import com.brain.help.service.endereco.EditarEnderecoService;
import com.brain.help.utils.ImageFileWriter;

@Service
public class EditarDiagnosticadoService {
	
	@Autowired
	BuscarDiagnosticadoPorEmailService buscarDiagnosticado;
	
	@Autowired
	EditarEnderecoService editarEndereco;
	
	@Autowired
	DiagnosticadoRepository diagnosticadoRepository;
	
	public void editar(String emailDiagnosticado, Dependente dependente) throws Exception {
		Dependente dependenteParaEditar = buscarDiagnosticado.buscar(emailDiagnosticado, false);
		
		if (!Objects.isNull(dependente.getNome()) && !dependente.getNome().isEmpty()) {
			dependenteParaEditar.setNome(dependente.getNome());
		}
		
		if (!Objects.isNull(dependente.getSobrenome()) && !dependente.getSobrenome().isEmpty()) {
			dependenteParaEditar.setSobrenome(dependente.getSobrenome());
		}
		
		if (!Objects.isNull(dependente.getTelefone()) && !dependente.getTelefone().isEmpty()) {
			dependenteParaEditar.setTelefone(dependente.getTelefone());
		}
		
		if (!Objects.isNull(dependente.getChaveSeguranca()) && !dependente.getChaveSeguranca().isEmpty()) {
			dependenteParaEditar.setChaveSeguranca(dependente.getChaveSeguranca());
		}
		
		if (!Objects.isNull(dependente.getTelefone()) && !dependente.getTelefone().isEmpty()) {
			dependenteParaEditar.setTelefone(dependente.getTelefone());
		}
		
		if (!Objects.isNull(dependente.getIdade()) && dependente.getIdade() != 0) {
			dependenteParaEditar.setIdade(dependente.getIdade());
		}
		
		if (!Objects.isNull(dependente.getSexo())) {
			dependenteParaEditar.setSexo(dependente.getSexo());
		}
		
		if (!Objects.isNull(dependente.getDataDiagnostico())) {
			dependenteParaEditar.setDataDiagnostico(dependente.getDataDiagnostico());
		}
		
		if (!Objects.isNull(dependente.getEstagioAlzheimer())) {
			dependenteParaEditar.setEstagioAlzheimer(dependente.getEstagioAlzheimer());
		}
		
		if (!Objects.isNull(dependente.getEndereco())) {
			editarEndereco.editar(dependente.getEndereco());
		}
		
		if (!Objects.isNull(dependente.getFoto()) && !dependente.getFoto().isEmpty()) {
			if(dependenteParaEditar.getFoto() != null && !dependenteParaEditar.getFoto().isEmpty()) {
				ImageFileWriter.deleteFile(dependenteParaEditar.getFoto());
			}
			String base64 = dependente.getFoto();
			dependenteParaEditar.setFoto(ImageFileWriter.saveImageToFolder(null, base64));
		}
		
		diagnosticadoRepository.save(dependenteParaEditar);
	}
}
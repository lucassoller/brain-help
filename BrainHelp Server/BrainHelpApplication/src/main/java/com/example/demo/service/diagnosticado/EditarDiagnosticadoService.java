package com.example.demo.service.diagnosticado;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Diagnosticado;
import com.example.demo.repository.DiagnosticadoRepository;
import com.example.demo.service.endereco.EditarEnderecoService;
import com.example.demo.utils.ImageFileWriter;

@Service
public class EditarDiagnosticadoService {
	
	@Autowired
	BuscarDiagnosticadoPorEmailService buscarDiagnosticado;
	
	@Autowired
	EditarEnderecoService editarEndereco;
	
	@Autowired
	DiagnosticadoRepository diagnosticadoRepository;
	
	public void editar(String emailDiagnosticado, Diagnosticado diagnosticado) throws Exception {
		Diagnosticado diagnosticadoParaEditar = buscarDiagnosticado.buscar(emailDiagnosticado);
		
		if (!Objects.isNull(diagnosticado.getNome()) && !diagnosticado.getNome().isEmpty()) {
			diagnosticadoParaEditar.setNome(diagnosticado.getNome());
		}
		
		if (!Objects.isNull(diagnosticado.getSobrenome()) && !diagnosticado.getSobrenome().isEmpty()) {
			diagnosticadoParaEditar.setSobrenome(diagnosticado.getSobrenome());
		}
		
		if (!Objects.isNull(diagnosticado.getTelefone()) && !diagnosticado.getTelefone().isEmpty()) {
			diagnosticadoParaEditar.setTelefone(diagnosticado.getTelefone());
		}
		
		if (!Objects.isNull(diagnosticado.getChaveSeguranca()) && !diagnosticado.getChaveSeguranca().isEmpty()) {
			diagnosticadoParaEditar.setChaveSeguranca(diagnosticado.getChaveSeguranca());
		}
		
		if (!Objects.isNull(diagnosticado.getTelefone()) && !diagnosticado.getTelefone().isEmpty()) {
			diagnosticadoParaEditar.setTelefone(diagnosticado.getTelefone());
		}
		
		if (!Objects.isNull(diagnosticado.getIdade()) && diagnosticado.getIdade() != 0) {
			diagnosticadoParaEditar.setIdade(diagnosticado.getIdade());
		}
		
		if (!Objects.isNull(diagnosticado.getSexo())) {
			diagnosticadoParaEditar.setSexo(diagnosticado.getSexo());
		}
		
		if (!Objects.isNull(diagnosticado.getDataDiagnostico())) {
			diagnosticadoParaEditar.setDataDiagnostico(diagnosticado.getDataDiagnostico());
		}
		
		if (!Objects.isNull(diagnosticado.getEstagioAlzheimer())) {
			diagnosticadoParaEditar.setEstagioAlzheimer(diagnosticado.getEstagioAlzheimer());
		}
		
		if (!Objects.isNull(diagnosticado.getEndereco())) {
			editarEndereco.editar(diagnosticado.getEndereco());		
		}
		
		if (!Objects.isNull(diagnosticado.getFoto()) && !diagnosticado.getFoto().isEmpty()) {
			if(diagnosticadoParaEditar.getFoto() != null && !diagnosticadoParaEditar.getFoto().isEmpty()) {
				ImageFileWriter.deleteFile(diagnosticadoParaEditar.getFoto());
			}
			String base64 = diagnosticado.getFoto();
			diagnosticadoParaEditar.setFoto(ImageFileWriter.saveImageToFolder(null, base64));
		}
		
		diagnosticadoRepository.save(diagnosticadoParaEditar);
	}
}
package com.example.demo.service.diagnosticado;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Diagnosticado;
import com.example.demo.service.endereco.EditarEnderecoService;

@Service
public class EditarDiagnosticadoService {
	
	@Autowired
	BuscarDiagnosticadoPorEmailService buscarDiagnosticado;
	
	@Autowired
	EditarEnderecoService editarEndereco;
	
	public void editar(String emailDiagnosticado, Diagnosticado diagnosticado) {
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
			editarEndereco.editar(diagnosticadoParaEditar.getEndereco(), diagnosticado.getEndereco());		
		}
	}
}
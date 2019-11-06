package com.example.demo.service.diagnosticado;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.example.demo.model.Diagnosticado;
import com.example.demo.repository.DiagnosticadoRepository;
import com.example.demo.service.endereco.EditarEnderecoService;

@Service
public class EditarFotoDiagnosticadoService {
	
	@Autowired
	DiagnosticadoRepository diagnosticadoRepository;
	
	@Autowired
	EditarEnderecoService editarEndereco;
	
	private static String UPLOADED_FOLDER = System.getProperty("user.dir") + "/src/main/resources/imagens/";

	public void editar(MultipartFile file, String email) {
		Diagnosticado diagnosticadoParaEditar = diagnosticadoRepository.findByEmail(email)
				.orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
		
		if (!file.isEmpty()) {
        	try {
                byte[] bytes = file.getBytes();
                String extensao = file.getContentType().split("/")[1];
                String fileName = new Date().getTime() + diagnosticadoParaEditar.getEmail() + "." + extensao;
                Path path = Paths.get(UPLOADED_FOLDER + fileName);
                Files.write(path, bytes);
                if(!Objects.isNull(diagnosticadoParaEditar.getFoto()) && !diagnosticadoParaEditar.getFoto().isEmpty()) {
                	File fotoAntiga = new File(UPLOADED_FOLDER + diagnosticadoParaEditar.getFoto());
                	fotoAntiga.delete();
                }
                diagnosticadoParaEditar.setFoto(fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
       }  
		diagnosticadoRepository.save(diagnosticadoParaEditar);
	}
}

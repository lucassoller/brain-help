package com.example.demo.service.medico;

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

import com.example.demo.model.Medico;
import com.example.demo.repository.MedicoRepository;
import com.example.demo.service.endereco.EditarEnderecoService;

@Service
public class EditarFotoMedicoService {
	
	@Autowired
	MedicoRepository medicoRepository;
	
	@Autowired
	EditarEnderecoService editarEndereco;
	
	private static String UPLOADED_FOLDER = System.getProperty("user.dir") + "/src/main/resources/imagens/";

	public void editar(MultipartFile file, String email) {
		Medico medicoParaEditar = medicoRepository.findByEmail(email)
				.orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
		
		if (!file.isEmpty()) {
        	try {
                byte[] bytes = file.getBytes();
                String extensao = file.getContentType().split("/")[1];
                String fileName = new Date().getTime() + medicoParaEditar.getEmail() + "." + extensao;
                Path path = Paths.get(UPLOADED_FOLDER + fileName);
                Files.write(path, bytes);
                if(!Objects.isNull(medicoParaEditar.getFoto()) && !medicoParaEditar.getFoto().isEmpty()) {
                	File fotoAntiga = new File(UPLOADED_FOLDER + medicoParaEditar.getFoto());
                	fotoAntiga.delete();
                }
                medicoParaEditar.setFoto(fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
       }  
		medicoRepository.save(medicoParaEditar);
	}
}

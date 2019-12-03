package com.example.demo.service.fotografia;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Fotografia;
import com.example.demo.repository.FotografiaRepository;
import com.example.demo.utils.ImageFileWriter;

@Service
public class EditarFotografiaService {
	
	@Autowired
	BuscarFotografiaPorIdService buscarFotografia;
	
	@Autowired
	FotografiaRepository fotografiaRepository;
	
	public void editar(Fotografia fotografia) throws Exception {
		Fotografia fotografiaParaEditar = buscarFotografia.buscar(fotografia.getCodFotografia());
		
		if (!Objects.isNull(fotografia.getLugar()) && !(fotografia.getLugar().isEmpty())) {
			fotografiaParaEditar.setLugar(fotografia.getLugar());
		}
		
		if (!Objects.isNull(fotografia.getLembrancas()) && !fotografia.getLembrancas().isEmpty()) {
			fotografiaParaEditar.setLembrancas(fotografia.getLembrancas());
		}
		
		if (!Objects.isNull(fotografia.getFoto()) && !fotografia.getFoto().isEmpty()) {
			if(!fotografiaParaEditar.getFoto().isEmpty()) {
				ImageFileWriter.deleteFile(fotografiaParaEditar.getFoto());
			}
			String base64 = fotografia.getFoto();
			fotografiaParaEditar.setFoto(ImageFileWriter.saveImageToFolder(null, base64));
		}
		
		if (!Objects.isNull(fotografia.getData())){
			fotografiaParaEditar.setData(fotografia.getData());
		}
	
		fotografiaRepository.save(fotografiaParaEditar);
	}
}
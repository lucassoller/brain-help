package com.example.demo.service.fotografia;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Fotografia;
import com.example.demo.repository.FotografiaRepository;

@Service
public class EditarFotografiaService {
	
	@Autowired
	BuscarFotografiaPorIdService buscarFotografia;
	
	@Autowired
	FotografiaRepository fotografiaRepository;
	
	public void editar(Integer codFotografia, Fotografia fotografia) {
		Fotografia fotografiaParaEditar = buscarFotografia.buscar(codFotografia);
		
		if (!Objects.isNull(fotografia.getLugar()) && !(fotografia.getLugar().isEmpty())) {
			fotografiaParaEditar.setLugar(fotografia.getLugar());
		}
		
		if (!Objects.isNull(fotografia.getDescricao()) && !fotografia.getDescricao().isEmpty()) {
			fotografiaParaEditar.setDescricao(fotografia.getDescricao());
		}
		
		if (!Objects.isNull(fotografia.getLembrancas()) && !fotografia.getLembrancas().isEmpty()) {
			fotografiaParaEditar.setLembrancas(fotografia.getLembrancas());
		}
		
		if (!Objects.isNull(fotografia.getFoto()) && !fotografia.getFoto().isEmpty()) {
			fotografiaParaEditar.setFoto(fotografia.getFoto());
		}
		
		if (!Objects.isNull(fotografia.getData())){
			fotografiaParaEditar.setData(fotografia.getData());
		}
	
		fotografiaRepository.save(fotografiaParaEditar);
	}
}
package com.brain.help.service.fotografia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.brain.help.entity.Fotografia;
import com.brain.help.repository.FotografiaRepository;

@Service
public class DeletarFotografiaService {

	@Autowired
	BuscarFotografiaPorIdService buscarFotografia;
	
	@Autowired
	FotografiaRepository fotografiaRepository;
	
	public void deletar(Integer codFotografia) {
		Fotografia fotografia = buscarFotografia.buscar(codFotografia);
		fotografiaRepository.delete(fotografia);
	}
}

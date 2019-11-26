package com.example.demo.service.fotografia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Fotografia;
import com.example.demo.repository.FotografiaRepository;

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

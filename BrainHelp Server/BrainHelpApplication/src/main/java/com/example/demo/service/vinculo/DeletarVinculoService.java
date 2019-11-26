package com.example.demo.service.vinculo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Vinculo;
import com.example.demo.repository.VinculoRepository;

@Service
public class DeletarVinculoService {

	@Autowired
	BuscarVinculoPorIdService buscarVinculo;
	
	@Autowired
	VinculoRepository vinculoRepository;
	
	public void deletar(Integer codVinculo) {
		Vinculo vinculo = buscarVinculo.buscar(codVinculo);
		vinculoRepository.delete(vinculo);
	}
}

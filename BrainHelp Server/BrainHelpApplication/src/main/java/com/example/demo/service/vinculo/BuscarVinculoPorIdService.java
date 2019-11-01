package com.example.demo.service.vinculo;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Vinculo;
import com.example.demo.repository.VinculoRepository;

@Service
public class BuscarVinculoPorIdService {

	@Autowired
	VinculoRepository vinculoRepository;

	public Vinculo buscar(Integer codTarefa) {
		if ((Objects.isNull(codTarefa))) {
			throw new IllegalArgumentException("O código do vínculo não pode estar em branco");
		}
		
		return vinculoRepository.findById(codTarefa)
				.orElseThrow(() -> new IllegalArgumentException("Nenhum vínculo foi encontrado"));
	}
}
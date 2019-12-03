package com.example.demo.service.medico;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Medico;
import com.example.demo.repository.MedicoRepository;
import com.example.demo.service.endereco.EditarEnderecoService;

@Service
public class EditarMedicoService {
	
	@Autowired
	MedicoRepository medicoRepository;
	
	@Autowired
	EditarEnderecoService editarEndereco;

	public void editar(String email, Medico medico) throws Exception {
		Medico medicoParaEditar = medicoRepository.findByEmail(email)
				.orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
		if (!Objects.isNull(medico.getNome()) && !medico.getNome().isEmpty()) {
			medicoParaEditar.setNome(medico.getNome());
		}
		if (!Objects.isNull(medico.getSobrenome()) && !medico.getSobrenome().isEmpty()) {
			medicoParaEditar.setSobrenome(medico.getSobrenome());
		}
		if (!Objects.isNull(medico.getTelefone()) && !medico.getTelefone().isEmpty()) {
			medicoParaEditar.setTelefone(medico.getTelefone());
		}
		if (!Objects.isNull(medico.getEspecializacao()) && !medico.getEspecializacao().isEmpty()) {
			medicoParaEditar.setEspecializacao(medico.getEspecializacao());
		}
		if(!Objects.isNull(medico.getEndereco())) {
			editarEndereco.editar(medico.getEndereco());
		}
		medicoRepository.save(medicoParaEditar);
	}
}

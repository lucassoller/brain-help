package com.brain.help.service.vinculo;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.brain.help.entity.Dependente;
import com.brain.help.entity.Vinculo;
import com.brain.help.repository.VinculoRepository;
import com.brain.help.service.dependente.BuscarDiagnosticadoPorEmailService;
import com.brain.help.service.endereco.CadastrarEnderecoService;
import com.brain.help.utils.ImageFileWriter;

@Service
public class CadastrarVinculoService {
	
	@Autowired
	VinculoRepository vinculoRepository;
	
	@Autowired
	CadastrarEnderecoService cadastrarEndereco;
	
	@Autowired
	BuscarDiagnosticadoPorEmailService buscarDiagnosticado;

	public void salvar(String emailDiagnosticado, Vinculo vinculo) throws Exception {
		Dependente dependente = buscarDiagnosticado.buscar(emailDiagnosticado, false);
		
		if (Objects.isNull(vinculo.getNome()) || vinculo.getNome().isEmpty()) {
			throw new IllegalArgumentException("O nome não pode estar em branco");
		}
		
		if (Objects.isNull(vinculo.getSobrenome()) || vinculo.getSobrenome().isEmpty()) {
			throw new IllegalArgumentException("O sobrenome não pode estar em branco");
		}
		
		if (Objects.isNull(vinculo.getTelefone()) || vinculo.getTelefone().isEmpty()) {
			throw new IllegalArgumentException("O telefone não pode estar em branco");
		}
		
		if (Objects.isNull(vinculo.getVinculo()) || vinculo.getVinculo().isEmpty()) {
			throw new IllegalArgumentException("O vínculo não pode estar em branco");
		}
		
		if (Objects.isNull(vinculo.getSexo())) {
			throw new IllegalArgumentException("O sexo não pode estar em branco");
		}
		
		if (Objects.isNull(vinculo.getIdade())) {
			throw new IllegalArgumentException("A idade não pode estar em branco");
		}
		
		if (Objects.isNull(vinculo.getIdade() < 0 || vinculo.getIdade() > 110)) {
			throw new IllegalArgumentException("A idade deve estar entre 0 e 110 anos");
		}
		
		if(!Objects.isNull(vinculo.getEndereco())){
			cadastrarEndereco.salvar(vinculo.getEndereco());
		}
		
		if(!Objects.isNull(vinculo.getFoto()) && !vinculo.getFoto().isEmpty()) {
			String base64 = vinculo.getFoto();
			vinculo.setFoto(ImageFileWriter.saveImageToFolder(null, base64));
		}
		vinculo.setDependente(dependente);
		vinculoRepository.save(vinculo);
	}
}
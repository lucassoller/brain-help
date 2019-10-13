package com.example.demo.service.vinculo;

import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Diagnosticado;
import com.example.demo.model.Lembranca;
import com.example.demo.model.Vinculo;
import com.example.demo.repository.VinculoRepository;
import com.example.demo.service.diagnosticado.BuscarDiagnosticadoPorEmailService;
import com.example.demo.service.endereco.CadastrarEnderecoService;
import com.example.demo.service.lembranca.CadastrarLembrancaService;

@Service
public class CadastrarVinculoService {
	
	@Autowired
	VinculoRepository vinculoRepository;
	
	@Autowired
	CadastrarEnderecoService cadastrarEndereco;
	
	@Autowired
	CadastrarLembrancaService cadastrarLembranca;	
	
	@Autowired
	BuscarDiagnosticadoPorEmailService buscarDiagnosticado;

	public void salvar(Diagnosticado diagnosticado, Vinculo vinculo) {
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
		
		vinculo.setDiagnosticado(diagnosticado);
		vinculoRepository.save(vinculo);
		
		List<Lembranca> lembrancas = vinculo.getLembrancas();
		if(!Objects.isNull(lembrancas) && !lembrancas.isEmpty()) {
			for(Lembranca lembranca : lembrancas) {
				cadastrarLembranca.salvar(lembranca, vinculo);
			}
		}
	}
	
	public void salvar(String emailDiagnosticado, Vinculo vinculo) {
		Diagnosticado diagnosticado = buscarDiagnosticado.buscar(emailDiagnosticado);
		salvar(diagnosticado, vinculo);
	}
}
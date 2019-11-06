package com.example.demo.service.diagnosticado;

import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Diagnosticado;
import com.example.demo.model.Fotografia;
import com.example.demo.model.Medicamento;
import com.example.demo.model.Musica;
import com.example.demo.model.Tarefa;
import com.example.demo.model.Vinculo;
import com.example.demo.model.security.password.Criptografia;
import com.example.demo.repository.DiagnosticadoRepository;
import com.example.demo.service.endereco.CadastrarEnderecoService;
import com.example.demo.service.fotografia.CadastrarFotografiaService;
import com.example.demo.service.medicamento.CadastrarMedicamentoService;
import com.example.demo.service.musica.CadastrarMusicaService;
import com.example.demo.service.tarefa.CadastrarTarefaService;
import com.example.demo.service.vinculo.CadastrarVinculoService;

@Service
public class CadastrarDiagnosticadoService {

	@Autowired
	DiagnosticadoRepository diagnosticadoRepository;

	@Autowired
	CadastrarEnderecoService cadastrarEndereco;
	
	@Autowired
	CadastrarVinculoService cadastrarVinculo;
	
	@Autowired
	CadastrarMedicamentoService cadastrarMedicamento;
	
	@Autowired
	CadastrarFotografiaService cadastrarFotografia;
	
	@Autowired
	CadastrarMusicaService cadastrarMusica;
	
	@Autowired
	CadastrarTarefaService cadastrarTarefa;
	
	@Autowired
	BuscarEmailDiagnosticadoUsadoService buscarEmailUsadoService;

	public void salvar(Diagnosticado diagnosticado) {
		if (Objects.isNull(diagnosticado.getNome()) || diagnosticado.getNome().isEmpty()) {
			throw new IllegalArgumentException("O nome não pode estar em branco");
		}
		
		if (Objects.isNull(diagnosticado.getSobrenome()) || diagnosticado.getSobrenome().isEmpty()) {
			throw new IllegalArgumentException("O sobrenome não pode estar em branco");
		}
		
		if (Objects.isNull(diagnosticado.getEmail()) || diagnosticado.getEmail().isEmpty()) {
			throw new IllegalArgumentException("O email não pode estar em branco");
		}
		
		if (Objects.isNull(diagnosticado.getTelefone()) || diagnosticado.getTelefone().isEmpty()) {
			throw new IllegalArgumentException("O telefone não pode estar em branco");
		}
		
		if (Objects.isNull(diagnosticado.getSenha()) || diagnosticado.getSenha().isEmpty()) {
			throw new IllegalArgumentException("A senha não pode estar em branco");
		}
		
		if (Objects.isNull(diagnosticado.getIdade())) {
			throw new IllegalArgumentException("A idade não pode estar em branco");
		}
		
		if (diagnosticado.getIdade() > 110 || diagnosticado.getIdade() < 20) {
			throw new IllegalArgumentException("A idade deve estar entre 20 e 110 anos");
		}
		
		if (Objects.isNull(diagnosticado.getSexo())) {
			throw new IllegalArgumentException("O sexo não pode estar em branco");
		}
		
		if (Objects.isNull(diagnosticado.getEstagioAlzheimer())) {
			throw new IllegalArgumentException("O est´agio da doença não pode estar em branco");
		}
		
		if (Objects.isNull(diagnosticado.getEndereco())) {
			throw new IllegalArgumentException("O endereço não pode estar em branco");
		}
		
		buscarEmailUsadoService.buscar(diagnosticado.getEmail());
		
		Criptografia criptografia = new Criptografia();
		diagnosticado.setSenha(criptografia.criptografarSenha(diagnosticado.getSenha()));
		
		cadastrarEndereco.salvar(diagnosticado.getEndereco());
		diagnosticadoRepository.save(diagnosticado);
		
		List<Vinculo> vinculos = diagnosticado.getVinculos();
		if(!Objects.isNull(vinculos) && !vinculos.isEmpty()) {
			for(Vinculo vinculo : vinculos) {
				cadastrarVinculo.salvar(diagnosticado, vinculo);
			}
		}
		
		List<Medicamento> medicamentos = diagnosticado.getMedicamentos();
		if(!Objects.isNull(medicamentos) && !medicamentos.isEmpty()) {
			for(Medicamento medicamento : medicamentos) {
				cadastrarMedicamento.salvar(diagnosticado, medicamento);
			}
		}
		
		List<Fotografia> fotografias = diagnosticado.getFotografias();
		if(!Objects.isNull(fotografias) && !fotografias.isEmpty()) {
			for(Fotografia fotografia : fotografias) {
				cadastrarFotografia.salvar(diagnosticado, fotografia);
			}
		}
		
		List<Musica> musicas = diagnosticado.getMusicas();
		if(!Objects.isNull(musicas) && !musicas.isEmpty()) {
			for(Musica musica : musicas) {
				cadastrarMusica.salvar(diagnosticado, musica);
			}
		}
		
		List<Tarefa> tarefas = diagnosticado.getTarefas();
		if(!Objects.isNull(tarefas) && !tarefas.isEmpty()) {
			for(Tarefa tarefa : tarefas) {
				cadastrarTarefa.salvar(diagnosticado, tarefa);
			}
		}

	}
}
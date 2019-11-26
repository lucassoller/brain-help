package com.example.demo.web;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.Atividade;
import com.example.demo.repository.AtividadeRepository;
import com.example.demo.service.atividade.BuscarAtividadePorIdService;
import com.example.demo.service.atividade.CadastrarAtividadeService;
import com.example.demo.service.atividade.DeletarAtividadeService;
import com.example.demo.service.atividade.EditarAtividadeService;

@RestController
@RequestMapping("/atividade")
public class AtividadeController {
	
	@Autowired
	private AtividadeRepository atividadeRepository;
	
	@Autowired
	private CadastrarAtividadeService cadastrarAtividade;
	
	@Autowired
	private BuscarAtividadePorIdService buscarAtividadePorId;
	
	@Autowired
	private EditarAtividadeService editarAtividade;
	
	@Autowired
	private DeletarAtividadeService deletarAtividade;
	
	@GetMapping("/buscar/todos")
	public List<Atividade> buscarTodos(){
		return atividadeRepository.findAll();
	}
	
	@GetMapping("/buscar/{ID}")
	public Atividade buscarPorId(@PathVariable("ID") Integer codAtividade){
		return buscarAtividadePorId.buscar(codAtividade);
	}
	
	@PostMapping("/cadastrar")
	public void cadastrarAtividade(@RequestBody Atividade atividade) {
		cadastrarAtividade.salvar(atividade);
	}
	
	@PutMapping("/editar/{ID}")
	public void editarAtividade(@PathVariable("ID") Integer codAtividade, @RequestBody Atividade atividade) {
		editarAtividade.editar(codAtividade, atividade);
	}
	
	@DeleteMapping("/deletar/{ID}")
	public void deletarAtividade(@PathVariable("ID") Integer codAtividade) {
		deletarAtividade.deletar(codAtividade);
	}
}
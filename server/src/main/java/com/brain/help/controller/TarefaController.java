package com.brain.help.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.brain.help.entity.Tarefa;
import com.brain.help.repository.TarefaRepository;
import com.brain.help.service.tarefa.BuscarTarefaPorIdService;
import com.brain.help.service.tarefa.BuscarTarefasDoUsuarioService;
import com.brain.help.service.tarefa.CadastrarTarefaService;
import com.brain.help.service.tarefa.DeletarTarefaService;
import com.brain.help.service.tarefa.EditarTarefaService;

@RestController
@RequestMapping("/tarefa")
public class TarefaController {
	
	@Autowired
	private TarefaRepository tarefaRepository;
	
	@Autowired
	private CadastrarTarefaService cadastrarTarefa;
	
	@Autowired
	private BuscarTarefaPorIdService buscarTarefaPorId;
	
	@Autowired
	private EditarTarefaService editarTarefa;
	
	@Autowired
	private DeletarTarefaService deletarTarefa;
	
	@Autowired
	private BuscarTarefasDoUsuarioService buscarTarefasDoUsuario;
	
	@GetMapping("/buscar/todos")
	public List<Tarefa> buscarTodos(){
		return tarefaRepository.findAll();
	}
	
	@GetMapping("/buscar/todos/usuario")
	public List<Tarefa> buscarTodosDoUsuario(@AuthenticationPrincipal UserPrincipal userPrincipal){
		return buscarTarefasDoUsuario.buscar(userPrincipal.getEmail());
	}
	
	@GetMapping("/buscar/{ID}")
	public Tarefa buscarPorId(@PathVariable("ID") Integer codTarefa){
		return buscarTarefaPorId.buscar(codTarefa);
	}
	
	@PostMapping("/cadastrar")
	public void cadastrarTarefa(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody Tarefa tarefa) {
		cadastrarTarefa.salvar(userPrincipal.getEmail(), tarefa);
	}
	
	@PutMapping("/editar")
	public void editarTarefa(@RequestBody Tarefa tarefa) {
		editarTarefa.editar(tarefa);
	}
	
	@DeleteMapping("/deletar/{ID}")
	public void deletarTarefa(@PathVariable("ID") Integer codTarefa) {
		deletarTarefa.deletar(codTarefa);
	}
}
package com.example.demo.web;

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
import com.example.demo.model.Tarefa;
import com.example.demo.model.security.UserPrincipal;
import com.example.demo.repository.TarefaRepository;
import com.example.demo.service.tarefa.BuscarTarefaPorIdService;
import com.example.demo.service.tarefa.CadastrarTarefaService;
import com.example.demo.service.tarefa.DeletarTarefaService;
import com.example.demo.service.tarefa.EditarTarefaService;

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
	
	@GetMapping("/buscar/todos")
	public List<Tarefa> buscarTodos(){
		return tarefaRepository.findAll();
	}
	
	@GetMapping("/buscar/{ID}")
	public Tarefa buscarPorId(@PathVariable("ID") Integer codTarefa){
		return buscarTarefaPorId.buscar(codTarefa);
	}
	
	@PostMapping("/cadastrar")
	public void cadastrarTarefa(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody Tarefa tarefa) {
		cadastrarTarefa.salvar(userPrincipal.getEmail(), tarefa);
	}
	
	@PutMapping("/editar/{ID}")
	public void editarTarefa(@PathVariable("ID") Integer codTarefa, @RequestBody Tarefa tarefa) {
		editarTarefa.editar(codTarefa, tarefa);
	}
	
	@DeleteMapping("/deletar/{ID}")
	public void deletarTarefa(@PathVariable("ID") Integer codTarefa) {
		deletarTarefa.deletar(codTarefa);
	}
}
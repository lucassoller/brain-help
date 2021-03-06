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
import com.example.demo.model.Fotografia;
import com.example.demo.model.security.UserPrincipal;
import com.example.demo.repository.FotografiaRepository;
import com.example.demo.service.fotografia.BuscarFotografiaPorIdService;
import com.example.demo.service.fotografia.BuscarFotografiasDoUsuarioService;
import com.example.demo.service.fotografia.CadastrarFotografiaService;
import com.example.demo.service.fotografia.DeletarFotografiaService;
import com.example.demo.service.fotografia.EditarFotografiaService;

@RestController
@RequestMapping("/fotografia")
public class FotografiaController {
	
	@Autowired
	private FotografiaRepository fotografiaRepository;
	
	@Autowired
	private CadastrarFotografiaService cadastrarFotografia;
	
	@Autowired
	private BuscarFotografiaPorIdService buscarFotografiaPorId;
	
	@Autowired
	private EditarFotografiaService editarFotografia;
	
	@Autowired
	private DeletarFotografiaService deletarFotografia;
	
	@Autowired
	private BuscarFotografiasDoUsuarioService buscarFotografiasDoUsuario;
	
	@GetMapping("/buscar/todos")
	public List<Fotografia> buscarTodos(){
		return fotografiaRepository.findAll();
	}
	
	@GetMapping("/buscar/todos/usuario")
	public List<Fotografia> buscarTodosDoUsuario(@AuthenticationPrincipal UserPrincipal userPrincipal){
		return buscarFotografiasDoUsuario.buscar(userPrincipal.getEmail());
	}
	
	@GetMapping("/buscar/{ID}")
	public Fotografia buscarPorId(@PathVariable("ID") Integer codFotografia){
		return buscarFotografiaPorId.buscar(codFotografia);
	}
	
	@PostMapping("/cadastrar")
	public void cadastrarFotografia(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody Fotografia fotografia) throws Exception {
		cadastrarFotografia.salvar(userPrincipal.getEmail(), fotografia);
	}
	
	@PutMapping("/editar")
	public void editarFotografia(@RequestBody Fotografia fotografia) throws Exception {
		editarFotografia.editar(fotografia);
	}
	
	@DeleteMapping("/deletar/{ID}")
	public void deletarFotografia(@PathVariable("ID") Integer codFotografia) {
		deletarFotografia.deletar(codFotografia);
	}
}
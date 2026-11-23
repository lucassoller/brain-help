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
import com.brain.help.entity.Musica;
import com.brain.help.repository.MusicaRepository;
import com.brain.help.service.musica.BuscarMusicaPorIdService;
import com.brain.help.service.musica.BuscarMusicasDoUsuarioService;
import com.brain.help.service.musica.CadastrarMusicaService;
import com.brain.help.service.musica.DeletarMusicaService;
import com.brain.help.service.musica.EditarMusicaService;

@RestController
@RequestMapping("/musica")
public class MusicaController {
	
	@Autowired
	private MusicaRepository musicaRepository;
	
	@Autowired
	private CadastrarMusicaService cadastrarMusica;
	
	@Autowired
	private BuscarMusicaPorIdService buscarMusicaPorId;
	
	@Autowired
	private EditarMusicaService editarMusica;
	
	@Autowired
	private DeletarMusicaService deletarMusica;
	
	@Autowired
	private BuscarMusicasDoUsuarioService buscarMusicasDoUsuario;
	
	@GetMapping("/buscar/todos")
	public List<Musica> buscarTodos(){
		return musicaRepository.findAll();
	}
	
	@GetMapping("/buscar/todos/usuario")
	public List<Musica> buscarTodosDoUsuario(@AuthenticationPrincipal UserPrincipal userPrincipal){
		return buscarMusicasDoUsuario.buscar(userPrincipal.getEmail());
	}
	
	@GetMapping("/buscar/{ID}")
	public Musica buscarPorId(@PathVariable("ID") Integer codMusica){
		return buscarMusicaPorId.buscar(codMusica);
	}
	
	@PostMapping("/cadastrar")
	public void cadastrarMusica(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody Musica musica) {
		cadastrarMusica.salvar(userPrincipal.getEmail(), musica);
	}
	
	@PutMapping("/editar")
	public void editarMusica(@RequestBody Musica musica) {
		editarMusica.editar(musica);
	}
	
	@DeleteMapping("/deletar/{ID}")
	public void deletarMusica(@PathVariable("ID") Integer codMusica) {
		deletarMusica.deletar(codMusica);
	}
}
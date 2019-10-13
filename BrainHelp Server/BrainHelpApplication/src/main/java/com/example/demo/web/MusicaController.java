package com.example.demo.web;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.Musica;
import com.example.demo.model.security.UserPrincipal;
import com.example.demo.repository.MusicaRepository;
import com.example.demo.service.musica.BuscarMusicaPorIdService;
import com.example.demo.service.musica.CadastrarMusicaService;

@RestController
@RequestMapping("/musica")
public class MusicaController {
	
	@Autowired
	private MusicaRepository musicaRepository;
	
	@Autowired
	private CadastrarMusicaService cadastrarMusica;
	
	@Autowired
	private BuscarMusicaPorIdService buscarMusicaPorId;
	
	@GetMapping("/buscar/todos")
	public List<Musica> buscarTodos(){
		return musicaRepository.findAll();
	}
	
	@GetMapping("/buscar/{ID}")
	public Musica buscarPorId(@PathVariable("ID") Integer codMusica){
		return buscarMusicaPorId.buscar(codMusica);
	}
	
	@PostMapping("/cadastrar")
	public void cadastrarMusica(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody Musica musica) {
		cadastrarMusica.salvar(userPrincipal.getEmail(), musica);
	}
}

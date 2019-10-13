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
import com.example.demo.model.Fotografia;
import com.example.demo.model.security.UserPrincipal;
import com.example.demo.repository.FotografiaRepository;
import com.example.demo.service.fotografia.BuscarFotografiaPorIdService;
import com.example.demo.service.fotografia.CadastrarFotografiaService;

@RestController
@RequestMapping("/fotografia")
public class FotografiaController {
	
	@Autowired
	private FotografiaRepository fotografiaRepository;
	
	@Autowired
	private CadastrarFotografiaService cadastrarFotografia;
	
	@Autowired
	private BuscarFotografiaPorIdService buscarFotografiaPorId;
	
	@GetMapping("/buscar/todos")
	public List<Fotografia> buscarTodos(){
		return fotografiaRepository.findAll();
	}
	
	@GetMapping("/buscar/{ID}")
	public Fotografia buscarPorId(@PathVariable("ID") Integer codFotografia){
		return buscarFotografiaPorId.buscar(codFotografia);
	}
	
	@PostMapping("/cadastrar")
	public void cadastrarFotografia(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody Fotografia fotografia) {
		cadastrarFotografia.salvar(userPrincipal.getEmail(), fotografia);
	}
}

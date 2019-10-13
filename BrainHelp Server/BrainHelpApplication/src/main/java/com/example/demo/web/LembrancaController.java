package com.example.demo.web;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.Lembranca;
import com.example.demo.repository.LembrancaRepository;
import com.example.demo.service.lembranca.BuscarLembrancaPorIdService;
import com.example.demo.service.lembranca.CadastrarLembrancaService;

@RestController
@RequestMapping("/lembranca")
public class LembrancaController {
	
	@Autowired
	private LembrancaRepository lembrancaRepository;
	
	@Autowired
	private CadastrarLembrancaService cadastrarLembranca;
	
	@Autowired
	private BuscarLembrancaPorIdService buscarLembrancaPorId;
	
	@GetMapping("/buscar/todos")
	public List<Lembranca> buscarTodos(){
		return lembrancaRepository.findAll();
	}
	
	@GetMapping("/buscar/{ID}")
	public Lembranca buscarPorId(@PathVariable("ID") Integer codLembranca){
		return buscarLembrancaPorId.buscar(codLembranca);
	}
	
	@PostMapping("/cadastrar")
	public void cadastrarLembranca(@RequestBody Lembranca lembranca) {
		cadastrarLembranca.salvar(lembranca);
	}
}

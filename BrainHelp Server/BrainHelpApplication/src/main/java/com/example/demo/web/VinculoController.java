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
import com.example.demo.model.Vinculo;
import com.example.demo.model.security.UserPrincipal;
import com.example.demo.repository.VinculoRepository;
import com.example.demo.service.vinculo.BuscarVinculoPorIdService;
import com.example.demo.service.vinculo.CadastrarVinculoService;

@RestController
@RequestMapping("/vinculo")
public class VinculoController {
	
	@Autowired
	private VinculoRepository vinculoRepository;
	
	@Autowired 
	CadastrarVinculoService cadastrarVinculo;
	
	@Autowired
	BuscarVinculoPorIdService buscarVinculoPorId;
	
	@GetMapping("/buscar/todos")
	public List<Vinculo> buscarTodos(){
		return vinculoRepository.findAll();
	}
	
	@GetMapping("/buscar/{ID}")
	public Vinculo buscarPorId(@PathVariable("ID") Integer codVinculo){
		return buscarVinculoPorId.buscar(codVinculo);
	}
	
	@PostMapping("/cadastrar")
	public void cadastrarVinculo(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody Vinculo vinculo) {
		cadastrarVinculo.salvar(userPrincipal.getEmail(), vinculo);
	}
}

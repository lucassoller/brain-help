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
import com.example.demo.model.Vinculo;
import com.example.demo.model.security.UserPrincipal;
import com.example.demo.repository.VinculoRepository;
import com.example.demo.service.vinculo.BuscarVinculoPorIdService;
import com.example.demo.service.vinculo.BuscarVinculosDoUsuarioService;
import com.example.demo.service.vinculo.CadastrarVinculoService;
import com.example.demo.service.vinculo.DeletarVinculoService;
import com.example.demo.service.vinculo.EditarVinculoService;

@RestController
@RequestMapping("/vinculo")
public class VinculoController {
	
	@Autowired
	private VinculoRepository vinculoRepository;
	
	@Autowired 
	CadastrarVinculoService cadastrarVinculo;
	
	@Autowired
	BuscarVinculoPorIdService buscarVinculoPorId;
	
	@Autowired
	private EditarVinculoService editarVinculo;
	
	@Autowired
	private DeletarVinculoService deletarVinculo;
	
	@Autowired
	private BuscarVinculosDoUsuarioService buscarVinculosDoUsuario;
	
	@GetMapping("/buscar/todos")
	public List<Vinculo> buscarTodosVinculos(){
		return vinculoRepository.findAll();
	}
	
	@GetMapping("/buscar/todos/usuario")
	public List<Vinculo> buscarTodosVinculosDoUsuario(@AuthenticationPrincipal UserPrincipal userPrincipal){
		return buscarVinculosDoUsuario.buscar(userPrincipal.getEmail());
	}
	
	@GetMapping("/buscar/{ID}")
	public Vinculo buscarPorId(@PathVariable("ID") Integer codVinculo){
		return buscarVinculoPorId.buscar(codVinculo);
	}
	
	@PostMapping("/cadastrar")
	public void cadastrarVinculo(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody Vinculo vinculo) throws Exception {
		cadastrarVinculo.salvar(userPrincipal.getEmail(), vinculo);
	}
	
	@PutMapping("/editar")
	public void editarVinculo(@RequestBody Vinculo vinculo) throws Exception {
		editarVinculo.editar(vinculo);
	}
	
	@DeleteMapping("/deletar/{ID}")
	public void deletarVinculo(@PathVariable("ID") Integer codVinculo) {
		deletarVinculo.deletar(codVinculo);
	}
}
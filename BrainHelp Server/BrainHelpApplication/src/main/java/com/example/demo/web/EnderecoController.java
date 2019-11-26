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
import com.example.demo.model.Endereco;
import com.example.demo.repository.EnderecoRepository;
import com.example.demo.service.endereco.BuscarEnderecoPorIdService;
import com.example.demo.service.endereco.CadastrarEnderecoService;
import com.example.demo.service.endereco.DeletarEnderecoService;
import com.example.demo.service.endereco.EditarEnderecoService;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private CadastrarEnderecoService cadastrarEndereco;
	
	@Autowired
	private BuscarEnderecoPorIdService buscarEnderecoPorId;
	
	@Autowired
	private EditarEnderecoService editarEndereco;
	
	@Autowired
	private DeletarEnderecoService deletarEndereco;
	
	@GetMapping("/buscar/todos")
	public List<Endereco> buscarTodos(){
		return enderecoRepository.findAll();
	}
	
	@GetMapping("/buscar/{ID}")
	public Endereco buscarPorId(@PathVariable("ID") Integer codEndereco){
		return buscarEnderecoPorId.buscar(codEndereco);
	}
	
	@PostMapping("/cadastrar")
	public void cadastrarEndereco(@RequestBody Endereco endereco) {
		cadastrarEndereco.salvar(endereco);
	}
	
	@PutMapping("/editar/{ID}")
	public void editarEndereco(@PathVariable("ID") Integer codEndereco, @RequestBody Endereco endereco) {
		editarEndereco.editar(codEndereco, endereco);
	}
	
	@DeleteMapping("/deletar/{ID}")
	public void deletarEndereco(@PathVariable("ID") Integer codEndereco) {
		deletarEndereco.deletar(codEndereco);
	}
}
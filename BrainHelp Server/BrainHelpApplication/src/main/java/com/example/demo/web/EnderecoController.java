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
import com.example.demo.model.Endereco;
import com.example.demo.model.security.UserPrincipal;
import com.example.demo.repository.EnderecoRepository;
import com.example.demo.service.endereco.BuscarEnderecoPorIdService;
import com.example.demo.service.endereco.BuscarEnderecosDoUsuarioService;
import com.example.demo.service.endereco.CadastrarListaEnderecoService;
import com.example.demo.service.endereco.DeletarEnderecoService;
import com.example.demo.service.endereco.EditarEnderecoService;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private CadastrarListaEnderecoService cadastrarEndereco;
	
	@Autowired
	private BuscarEnderecoPorIdService buscarEnderecoPorId;
	
	@Autowired
	private EditarEnderecoService editarEndereco;
	
	@Autowired
	private DeletarEnderecoService deletarEndereco;
	
	@Autowired
	private BuscarEnderecosDoUsuarioService buscarEnderecosDoUsuario;
	
	
	@GetMapping("/buscar/todos")
	public List<Endereco> buscarTodos(){
		return enderecoRepository.findAll();
	}
	
	@GetMapping("/buscar/todos/usuario")
	public List<Endereco> buscarTodosDoDiagnosticado(@AuthenticationPrincipal UserPrincipal userPrincipal){
		return buscarEnderecosDoUsuario.buscar(userPrincipal.getEmail());
	}
	
	@GetMapping("/buscar/{ID}")
	public Endereco buscarPorId(@PathVariable("ID") Integer codEndereco){
		return buscarEnderecoPorId.buscar(codEndereco);
	}
	
	@PostMapping("/cadastrar")
	public void cadastrarEndereco(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody Endereco endereco) throws Exception {
		cadastrarEndereco.salvar(endereco, userPrincipal.getEmail());
	}
	
	@PutMapping("/editar")
	public void editarEndereco(@RequestBody Endereco endereco) throws Exception {
		editarEndereco.editar(endereco);
	}
	
	@DeleteMapping("/deletar/{ID}")
	public void deletarEndereco(@PathVariable("ID") Integer codEndereco) {
		deletarEndereco.deletar(codEndereco);
	}
}
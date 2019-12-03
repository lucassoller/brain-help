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
import com.example.demo.model.Medicamento;
import com.example.demo.model.security.UserPrincipal;
import com.example.demo.repository.MedicamentoRepository;
import com.example.demo.service.medicamento.BuscarMedicamentoPorIdService;
import com.example.demo.service.medicamento.BuscarMedicamentosDoUsuarioService;
import com.example.demo.service.medicamento.CadastrarMedicamentoService;
import com.example.demo.service.medicamento.DeletarMedicamentoService;
import com.example.demo.service.medicamento.EditarMedicamentoService;

@RestController
@RequestMapping("/medicamento")
public class MedicamentoController {
	
	@Autowired
	private MedicamentoRepository medicamentoRepository;
	
	@Autowired
	private CadastrarMedicamentoService cadastrarMedicamento;
	
	@Autowired
	private BuscarMedicamentoPorIdService buscarMedicamentoPorId;
	
	@Autowired
	private EditarMedicamentoService editarMedicamento;
	
	@Autowired
	private DeletarMedicamentoService deletarMedicamento;
	
	@Autowired
	private BuscarMedicamentosDoUsuarioService buscarMedicamentosDoUsuario;
	
	@GetMapping("/buscar/todos")
	public List<Medicamento> buscarTodos(){
		return medicamentoRepository.findAll();
	}
	
	@GetMapping("/buscar/todos/usuario")
	public List<Medicamento> buscarTodosDoUsuario(@AuthenticationPrincipal UserPrincipal userPrincipal){
		return buscarMedicamentosDoUsuario.buscar(userPrincipal.getEmail());
	}
	
	@GetMapping("/buscar/{ID}")
	public Medicamento buscarPorId(@PathVariable("ID") Integer codMedicamento){
		return buscarMedicamentoPorId.buscar(codMedicamento);
	}
	
	@PostMapping("/cadastrar")
	public void cadastrarMedicamento(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody Medicamento medicamento) throws Exception {
		cadastrarMedicamento.salvar(userPrincipal.getEmail(), medicamento);
	}
	
	@PutMapping("/editar")
	public void editarMedicamento(@RequestBody Medicamento medicamento) throws Exception {
		editarMedicamento.editar(medicamento);
	}
	
	@DeleteMapping("/deletar/{ID}")
	public void deletarMedicamento(@PathVariable("ID") Integer codMedicamento) {
		deletarMedicamento.deletar(codMedicamento);
	}
}
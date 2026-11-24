package com.brain.help.controller;

import java.util.List;

import com.brain.help.dto.dependente.DependenteDTO;
import com.brain.help.dto.dependente.DependenteResponseDTO;
import com.brain.help.dto.senha.MudancaSenhaDTO;
import com.brain.help.service.dependente.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dependentes")
public class DependenteController {

	private final DependenteService dependenteService;

    public DependenteController(DependenteService dependenteService) {
        this.dependenteService = dependenteService;
    }

	@PostMapping("/cadastrar")
	public ResponseEntity<DependenteResponseDTO> cadastrar(@Valid @RequestBody DependenteDTO dto) throws Exception {
		return ResponseEntity.status(HttpStatus.CREATED).body(dependenteService.criar(dto));
	}

    @GetMapping("/buscar/todos")
	public ResponseEntity<List<DependenteResponseDTO>> buscarTodos(){
		return ResponseEntity.ok(dependenteService.buscarTodos());
	}
	
	@GetMapping("/buscar/email")
	public ResponseEntity<DependenteResponseDTO> buscarPorEmail(@RequestParam String email){
		return ResponseEntity.ok(dependenteService.buscarPorEmail(email));
	}
	
	@GetMapping("/buscar/logado")
	public ResponseEntity<DependenteResponseDTO> buscarLogado(){
		return ResponseEntity.ok(dependenteService.buscarPerfilLogado());
	}

	/*
	
	@GetMapping("/buscar/todos/vinculados/{NOME}")
	public List<Dependente> buscarTodosVinculadosPorNome(@AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable("NOME") String nome){
		return buscarDiagnosticados.buscar(userPrincipal.getEmail(), nome, true);
	}
	
	@GetMapping("/buscar/todos/vinculados")
	public List<Dependente> buscarTodosVinculados(@AuthenticationPrincipal UserPrincipal userPrincipal){
		return buscarDiagnosticadosVinculados.buscar(userPrincipal.getEmail());
	}
	
	@GetMapping("/buscar/todos/desvinculados/{NOME}")
	public List<Dependente> buscarTodosNaoVinculados(@AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable("NOME") String nome){
		return buscarDiagnosticados.buscar(userPrincipal.getEmail(), nome, false);
	}

	*/

	
	@PutMapping("/editar")
	public ResponseEntity<DependenteResponseDTO> editar(@Valid @RequestBody DependenteDTO dto) throws Exception {
		return ResponseEntity.ok(dependenteService.atualizarPerfilLogado(dto));
	}

	@PutMapping("/editar/{id}")
	public ResponseEntity<DependenteResponseDTO> editarPorId(@Valid @RequestBody DependenteDTO dto,
															 @PathVariable Long id) throws Exception {
		return ResponseEntity.ok(dependenteService.atualizar(id, dto));
	}
	
	
	@PutMapping("/editar/senha")
	public ResponseEntity<DependenteResponseDTO> editarSenha(@Valid @RequestBody MudancaSenhaDTO dto) {
		return ResponseEntity.ok(dependenteService.atualizarSenhaLogado(dto));
	}
	
	@DeleteMapping("/deletar")
	public ResponseEntity<Void> deletarLogado(){
		dependenteService.deletarContaLogada();
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<Void> deletarPorId(@PathVariable Long id) {
		dependenteService.deletar(id);
		return ResponseEntity.noContent().build();
	}
}
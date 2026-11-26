package com.brain.help.controller;

import com.brain.help.dto.endereco.EnderecoDTO;
import com.brain.help.dto.endereco.EnderecoResponseDTO;
import com.brain.help.service.EnderecoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enderecos")
@RequiredArgsConstructor
public class EnderecoController {

	private final EnderecoService enderecoService;


	@PutMapping("/principal")
	public ResponseEntity<EnderecoResponseDTO> salvarOuEditarPrincipal(@Valid @RequestBody EnderecoDTO dto) throws Exception {
		return ResponseEntity.ok(enderecoService.salvarOuEditarPrincipal(dto));
	}

	@GetMapping("/principal")
	public ResponseEntity<EnderecoResponseDTO> buscarPrincipal() {
		return ResponseEntity.ok(enderecoService.buscarPrincipalDoLogado());
	}

	@PostMapping("/cadastrar")
	public ResponseEntity<EnderecoResponseDTO> cadastrar(@Valid @RequestBody EnderecoDTO dto) throws Exception {
		return ResponseEntity.status(HttpStatus.CREATED).body(enderecoService.cadastrar(dto));
	}

	@GetMapping("/buscar/todos")
	public ResponseEntity<List<EnderecoResponseDTO>> buscarTodos() {
		return ResponseEntity.ok(enderecoService.buscarTodosDoLogado());
	}

	@GetMapping("/buscar/{id}")
	public ResponseEntity<EnderecoResponseDTO> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok(enderecoService.buscarPorId(id));
	}

	@PutMapping("/editar/{id}")
	public ResponseEntity<EnderecoResponseDTO> editar(@PathVariable Long id,
													  @Valid @RequestBody EnderecoDTO dto) throws Exception {
		return ResponseEntity.ok(enderecoService.editar(id, dto));
	}

	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		enderecoService.deletar(id);
		return ResponseEntity.noContent().build();
	}
}
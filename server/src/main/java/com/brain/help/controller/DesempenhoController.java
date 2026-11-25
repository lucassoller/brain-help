package com.brain.help.controller;

import com.brain.help.dto.desempenho.DesempenhoDTO;
import com.brain.help.dto.desempenho.DesempenhoResponseDTO;
import com.brain.help.service.DesempenhoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/desempenhos")
@RequiredArgsConstructor
public class DesempenhoController {

	private final DesempenhoService desempenhoService;

	@PostMapping("/cadastrar")
	public ResponseEntity<DesempenhoResponseDTO> cadastrar(@Valid @RequestBody DesempenhoDTO dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(desempenhoService.cadastrar(dto));
	}

	@GetMapping("/buscar/todos")
	public ResponseEntity<List<DesempenhoResponseDTO>> buscarTodos() {
		return ResponseEntity.ok(desempenhoService.buscarTodosDoLogado());
	}

	@GetMapping("/buscar/{id}")
	public ResponseEntity<DesempenhoResponseDTO> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok(desempenhoService.buscarPorId(id));
	}

	@GetMapping("/buscar/dependente/{dependenteId}")
	public ResponseEntity<List<DesempenhoResponseDTO>> buscarPorDependente(@PathVariable Long dependenteId) {
		return ResponseEntity.ok(desempenhoService.buscarPorDependenteParaMedico(dependenteId));
	}

	@PutMapping("/editar/{id}")
	public ResponseEntity<DesempenhoResponseDTO> editar(@PathVariable Long id,
														@Valid @RequestBody DesempenhoDTO dto) {
		return ResponseEntity.ok(desempenhoService.editar(id, dto));
	}

	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		desempenhoService.deletar(id);
		return ResponseEntity.noContent().build();
	}
}
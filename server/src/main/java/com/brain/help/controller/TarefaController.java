package com.brain.help.controller;

import com.brain.help.dto.tarefa.TarefaDTO;
import com.brain.help.dto.tarefa.TarefaResponseDTO;
import com.brain.help.service.TarefaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
public class TarefaController {

	private final TarefaService tarefaService;

	@PostMapping("/cadastrar")
	public ResponseEntity<TarefaResponseDTO> cadastrar(@Valid @RequestBody TarefaDTO dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(tarefaService.cadastrar(dto));
	}

	@GetMapping("/buscar/todas")
	public ResponseEntity<List<TarefaResponseDTO>> buscarTodas() {
		return ResponseEntity.ok(tarefaService.buscarTodosDoLogado());
	}

	@GetMapping("/buscar/{id}")
	public ResponseEntity<TarefaResponseDTO> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok(tarefaService.buscarPorId(id));
	}

	@PutMapping("/editar/{id}")
	public ResponseEntity<TarefaResponseDTO> editar(@PathVariable Long id,
													@Valid @RequestBody TarefaDTO dto) {
		return ResponseEntity.ok(tarefaService.editar(id, dto));
	}

	@PatchMapping("/finalizar/{id}")
	public ResponseEntity<TarefaResponseDTO> finalizar(@PathVariable Long id) {
		return ResponseEntity.ok(tarefaService.finalizar(id));
	}

	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		tarefaService.deletar(id);
		return ResponseEntity.noContent().build();
	}
}
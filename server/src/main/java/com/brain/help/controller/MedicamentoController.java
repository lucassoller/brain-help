package com.brain.help.controller;

import com.brain.help.dto.medicamento.MedicamentoDTO;
import com.brain.help.dto.medicamento.MedicamentoResponseDTO;
import com.brain.help.service.MedicamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicamentos")
@RequiredArgsConstructor
public class MedicamentoController {

	private final MedicamentoService medicamentoService;

	@PostMapping("/cadastrar")
	public ResponseEntity<MedicamentoResponseDTO> cadastrar(@Valid @RequestBody MedicamentoDTO dto) throws Exception {
		return ResponseEntity.status(HttpStatus.CREATED).body(medicamentoService.cadastrar(dto));
	}

	@GetMapping("/buscar/todos")
	public ResponseEntity<List<MedicamentoResponseDTO>> buscarTodos() {
		return ResponseEntity.ok(medicamentoService.buscarTodosDoLogado());
	}

	@GetMapping("/buscar/{id}")
	public ResponseEntity<MedicamentoResponseDTO> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok(medicamentoService.buscarPorId(id));
	}

	@PutMapping("/editar/{id}")
	public ResponseEntity<MedicamentoResponseDTO> editar(@PathVariable Long id,
														 @Valid @RequestBody MedicamentoDTO dto) throws Exception {
		return ResponseEntity.ok(medicamentoService.editar(id, dto));
	}

	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		medicamentoService.deletar(id);
		return ResponseEntity.noContent().build();
	}
}
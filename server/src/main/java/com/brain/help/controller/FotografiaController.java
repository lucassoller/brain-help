package com.brain.help.controller;

import com.brain.help.dto.fotografia.FotografiaDTO;
import com.brain.help.dto.fotografia.FotografiaResponseDTO;
import com.brain.help.service.FotografiaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fotografias")
@RequiredArgsConstructor
public class FotografiaController {

	private final FotografiaService fotografiaService;

	@PostMapping("/cadastrar")
	public ResponseEntity<FotografiaResponseDTO> cadastrar(@Valid @RequestBody FotografiaDTO dto) throws Exception {
		return ResponseEntity.status(HttpStatus.CREATED).body(fotografiaService.cadastrar(dto));
	}

	@GetMapping("/buscar/todas")
	public ResponseEntity<List<FotografiaResponseDTO>> buscarTodas() {
		return ResponseEntity.ok(fotografiaService.buscarTodosDoLogado());
	}

	@GetMapping("/buscar/{id}")
	public ResponseEntity<FotografiaResponseDTO> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok(fotografiaService.buscarPorId(id));
	}

	@PutMapping("/editar/{id}")
	public ResponseEntity<FotografiaResponseDTO> editar(@PathVariable Long id,
														@Valid @RequestBody FotografiaDTO dto) throws Exception {
		return ResponseEntity.ok(fotografiaService.editar(id, dto));
	}

	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		fotografiaService.deletar(id);
		return ResponseEntity.noContent().build();
	}
}
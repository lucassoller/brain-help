package com.brain.help.controller;

import com.brain.help.dto.vinculo.VinculoDTO;
import com.brain.help.dto.vinculo.VinculoResponseDTO;
import com.brain.help.service.VinculoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vinculos")
@RequiredArgsConstructor
public class VinculoController {

	private final VinculoService vinculoService;

	@PostMapping("/cadastrar")
	public ResponseEntity<VinculoResponseDTO> cadastrar(@Valid @RequestBody VinculoDTO dto) throws Exception {
		return ResponseEntity.status(HttpStatus.CREATED).body(vinculoService.cadastrar(dto));
	}

	@GetMapping("/buscar/meus")
	public ResponseEntity<List<VinculoResponseDTO>> buscarMeusVinculos() {
		return ResponseEntity.ok(vinculoService.buscarVinculosDoLogado());
	}

	@GetMapping("/buscar/{id}")
	public ResponseEntity<VinculoResponseDTO> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok(vinculoService.buscarPorId(id));
	}

	@GetMapping("/buscar/nome")
	public ResponseEntity<List<VinculoResponseDTO>> buscarPorNome(@RequestParam String nome) {
		return ResponseEntity.ok(vinculoService.buscarPorNome(nome));
	}

	@GetMapping("/buscar/vinculo")
	public ResponseEntity<List<VinculoResponseDTO>> buscarPorVinculo(@RequestParam String vinculo) {
		return ResponseEntity.ok(vinculoService.buscarPorTipoVinculo(vinculo));
	}

	@PutMapping("/editar/{id}")
	public ResponseEntity<VinculoResponseDTO> editar(@PathVariable Long id,
													 @Valid @RequestBody VinculoDTO dto) throws Exception {
		return ResponseEntity.ok(vinculoService.editar(id, dto));
	}

	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		vinculoService.deletar(id);
		return ResponseEntity.noContent().build();
	}
}
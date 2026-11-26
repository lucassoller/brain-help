package com.brain.help.controller;

import com.brain.help.dto.musica.MusicaDTO;
import com.brain.help.dto.musica.MusicaResponseDTO;
import com.brain.help.service.MusicaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/musicas")
@RequiredArgsConstructor
public class MusicaController {

	private final MusicaService musicaService;

	@PostMapping("/cadastrar")
	public ResponseEntity<MusicaResponseDTO> cadastrar(@Valid @RequestBody MusicaDTO dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(musicaService.cadastrar(dto));
	}

	@GetMapping("/buscar/todas")
	public ResponseEntity<List<MusicaResponseDTO>> buscarTodas() {
		return ResponseEntity.ok(musicaService.buscarTodosDoLogado());
	}

	@GetMapping("/buscar/{id}")
	public ResponseEntity<MusicaResponseDTO> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok(musicaService.buscarPorId(id));
	}

	@GetMapping("/buscar")
	public ResponseEntity<List<MusicaResponseDTO>> buscarPorNomeOuCantor(@RequestParam(required = false) String termo) {
		return ResponseEntity.ok(musicaService.buscarPorNomeOuCantor(termo));
	}

	@PutMapping("/editar/{id}")
	public ResponseEntity<MusicaResponseDTO> editar(@PathVariable Long id,
													@Valid @RequestBody MusicaDTO dto) {
		return ResponseEntity.ok(musicaService.editar(id, dto));
	}

	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		musicaService.deletar(id);
		return ResponseEntity.noContent().build();
	}
}
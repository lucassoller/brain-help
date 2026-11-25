package com.brain.help.controller;

import com.brain.help.dto.medico.MedicoDTO;
import com.brain.help.dto.medico.MedicoResponseDTO;
import com.brain.help.dto.senha.MudancaSenhaDTO;
import com.brain.help.service.dependente.DependenteService;
import com.brain.help.service.medico.MedicoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
@RequiredArgsConstructor
public class MedicoController {

	private final MedicoService medicoService;
	private final DependenteService dependenteService;

	@PostMapping("/cadastrar")
	public ResponseEntity<MedicoResponseDTO> cadastrar(@Valid @RequestBody MedicoDTO dto) throws Exception {
		return ResponseEntity.status(HttpStatus.CREATED).body(medicoService.criar(dto));
	}

	@GetMapping("/buscar/todos")
	public ResponseEntity<List<MedicoResponseDTO>> buscarTodos() {
		return ResponseEntity.ok(medicoService.buscarTodos());
	}

	@GetMapping("/buscar/email")
	public ResponseEntity<MedicoResponseDTO> buscarPorEmail(@RequestParam String email) {
		return ResponseEntity.ok(medicoService.buscarPorEmail(email));
	}

	@GetMapping("/buscar/{id}")
	public ResponseEntity<MedicoResponseDTO> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok(medicoService.buscarPorId(id));
	}

	@GetMapping("/buscar/logado")
	public ResponseEntity<MedicoResponseDTO> buscarLogado() {
		return ResponseEntity.ok(medicoService.buscarPerfilLogado());
	}

	@PutMapping("/editar")
	public ResponseEntity<MedicoResponseDTO> editar(@Valid @RequestBody MedicoDTO dto) throws Exception {
		return ResponseEntity.ok(medicoService.atualizarPerfilLogado(dto));
	}

	@PutMapping("/editar/{id}")
	public ResponseEntity<MedicoResponseDTO> editarPorId(@Valid @RequestBody MedicoDTO dto,
														 @PathVariable Long id) throws Exception {
		return ResponseEntity.ok(medicoService.atualizar(id, dto));
	}

	@PutMapping("/editar/senha")
	public ResponseEntity<MedicoResponseDTO> editarSenha(@Valid @RequestBody MudancaSenhaDTO dto) {
		return ResponseEntity.ok(medicoService.atualizarSenhaLogado(dto));
	}

	@PatchMapping("/vincular/{dependenteId}")
	public ResponseEntity<Void> vincularDependente(@PathVariable Long dependenteId) {
		dependenteService.vincularAoMedicoLogado(dependenteId);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/deletar")
	public ResponseEntity<Void> deletarLogado() {
		medicoService.deletarContaLogada();
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<Void> deletarPorId(@PathVariable Long id) {
		medicoService.deletar(id);
		return ResponseEntity.noContent().build();
	}
}
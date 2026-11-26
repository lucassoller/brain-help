package com.brain.help.service;

import com.brain.help.dto.tarefa.TarefaDTO;
import com.brain.help.dto.tarefa.TarefaResponseDTO;
import com.brain.help.entity.Dependente;
import com.brain.help.entity.Tarefa;
import com.brain.help.enums.StatusTarefa;
import com.brain.help.exception.BusinessException;
import com.brain.help.exception.NotFoundException;
import com.brain.help.repository.TarefaRepository;
import com.brain.help.security.UsuarioLogadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefaService {

	private final TarefaRepository tarefaRepository;
	private final UsuarioLogadoService usuarioLogadoService;

	@Transactional
	public TarefaResponseDTO cadastrar(TarefaDTO dto) {
		Dependente dependenteLogado = usuarioLogadoService.getDependenteLogado();
		Tarefa entity = toEntity(dto, dependenteLogado);
		Tarefa salva = tarefaRepository.save(entity);
		return toDTO(salva);
	}

	@Transactional(readOnly = true)
	public List<TarefaResponseDTO> buscarTodosDoLogado() {
		Dependente dependenteLogado = usuarioLogadoService.getDependenteLogado();
		return tarefaRepository.findByDependenteIdOrderByDataCriacaoDesc(dependenteLogado.getId())
				.stream()
				.map(this::toDTO)
				.toList();
	}

	@Transactional(readOnly = true)
	public TarefaResponseDTO buscarPorId(Long id) {
		Tarefa entity = buscarTarefaPorId(id);
		return toDTO(entity);
	}

	@Transactional
	public TarefaResponseDTO editar(Long id, TarefaDTO dto) {
		Tarefa entity = buscarTarefaPorId(id);
		entity.setTarefa(dto.getTarefa());
		entity.setDescricao(dto.getDescricao());

		Tarefa atualizada = tarefaRepository.save(entity);
		return toDTO(atualizada);
	}

	@Transactional
	public TarefaResponseDTO finalizar(Long id) {
		Tarefa entity = buscarTarefaPorId(id);

		if (entity.getStatusTarefa() == StatusTarefa.CONCLUIDA) {
			throw new BusinessException("Esta tarefa já foi finalizada");
		}

		entity.setStatusTarefa(StatusTarefa.CONCLUIDA);
		entity.setDataFinalizacao(LocalDateTime.now());

		Tarefa finalizada = tarefaRepository.save(entity);
		return toDTO(finalizada);
	}

	@Transactional
	public void deletar(Long id) {
		Tarefa entity = buscarTarefaPorId(id);
		tarefaRepository.delete(entity);
	}

	private Tarefa buscarTarefaPorId(Long tarefaId) {
		return tarefaRepository.findById(tarefaId)
				.orElseThrow(() -> new NotFoundException("Tarefa não encontrada"));
	}

	private Tarefa toEntity(TarefaDTO dto, Dependente dependente) {
		Tarefa entity = new Tarefa();
		entity.setTarefa(dto.getTarefa());
		entity.setDescricao(dto.getDescricao());
		entity.setDataCriacao(LocalDateTime.now());
		entity.setStatusTarefa(StatusTarefa.NAO_CONCLUIDA);
		entity.setDependente(dependente);
		return entity;
	}

	private TarefaResponseDTO toDTO(Tarefa entity) {
		TarefaResponseDTO dto = new TarefaResponseDTO();
		dto.setId(entity.getId());
		dto.setTarefa(entity.getTarefa());
		dto.setDescricao(entity.getDescricao());
		dto.setDataCriacao(entity.getDataCriacao());
		dto.setDataFinalizacao(entity.getDataFinalizacao());
		dto.setStatusTarefa(entity.getStatusTarefa());
		return dto;
	}
}
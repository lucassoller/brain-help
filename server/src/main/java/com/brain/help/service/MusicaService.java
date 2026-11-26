package com.brain.help.service;

import com.brain.help.dto.musica.MusicaDTO;
import com.brain.help.dto.musica.MusicaResponseDTO;
import com.brain.help.entity.Dependente;
import com.brain.help.entity.Musica;
import com.brain.help.exception.NotFoundException;
import com.brain.help.repository.MusicaRepository;
import com.brain.help.security.UsuarioLogadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MusicaService {

	private final MusicaRepository musicaRepository;
	private final UsuarioLogadoService usuarioLogadoService;

	@Transactional
	public MusicaResponseDTO cadastrar(MusicaDTO dto) {
		Dependente dependenteLogado = usuarioLogadoService.getDependenteLogado();
		Musica entity = toEntity(dto, dependenteLogado);
		Musica salva = musicaRepository.save(entity);
		return toDTO(salva);
	}

	@Transactional(readOnly = true)
	public List<MusicaResponseDTO> buscarTodosDoLogado() {
		Long dependenteId = usuarioLogadoService.getDependenteIdLogado();
		return musicaRepository.findByDependenteIdOrderByNomeMusicaAsc(dependenteId)
				.stream()
				.map(this::toDTO)
				.toList();
	}

	@Transactional(readOnly = true)
	public List<MusicaResponseDTO> buscarPorNomeOuCantor(String termo) {
		if (termo == null || termo.isBlank()) {
			return buscarTodosDoLogado();
		}

		Long dependenteId = usuarioLogadoService.getDependenteIdLogado();
		return musicaRepository.buscarPorDependenteENomeOuCantor(dependenteId, termo.trim())
				.stream()
				.map(this::toDTO)
				.toList();
	}

	@Transactional(readOnly = true)
	public MusicaResponseDTO buscarPorId(Long id) {
		Musica entity = buscarMusicaDoDependente(id);
		return toDTO(entity);
	}

	@Transactional
	public MusicaResponseDTO editar(Long id, MusicaDTO dto) {
		Musica entity = buscarMusicaDoDependente(id);

		entity.setNomeMusica(dto.getNomeMusica());
		entity.setCantor(dto.getCantor());
		entity.setLembrancas(dto.getLembrancas());
		entity.setMusica(dto.getMusica());

		Musica atualizada = musicaRepository.save(entity);
		return toDTO(atualizada);
	}

	@Transactional
	public void deletar(Long id) {
		Musica entity = buscarMusicaDoDependente(id);
		musicaRepository.delete(entity);
	}

	private Musica buscarMusicaDoDependente(Long musicaId) {
		Long dependenteId = usuarioLogadoService.getDependenteIdLogado();
		return musicaRepository.findByIdAndDependenteId(musicaId, dependenteId)
				.orElseThrow(() -> new NotFoundException("Música não encontrada"));
	}

	private Musica toEntity(MusicaDTO dto, Dependente dependente) {
		Musica entity = new Musica();
		entity.setNomeMusica(dto.getNomeMusica());
		entity.setCantor(dto.getCantor());
		entity.setLembrancas(dto.getLembrancas());
		entity.setMusica(dto.getMusica());
		entity.setDependente(dependente);
		return entity;
	}

	private MusicaResponseDTO toDTO(Musica entity) {
		MusicaResponseDTO dto = new MusicaResponseDTO();
		dto.setId(entity.getId());
		dto.setNomeMusica(entity.getNomeMusica());
		dto.setCantor(entity.getCantor());
		dto.setLembrancas(entity.getLembrancas());
		dto.setMusica(entity.getMusica());
		return dto;
	}
}
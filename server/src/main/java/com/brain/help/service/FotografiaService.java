package com.brain.help.service;

import com.brain.help.dto.fotografia.FotografiaDTO;
import com.brain.help.dto.fotografia.FotografiaResponseDTO;
import com.brain.help.entity.Dependente;
import com.brain.help.entity.Fotografia;
import com.brain.help.exception.NotFoundException;
import com.brain.help.repository.FotografiaRepository;
import com.brain.help.security.UsuarioLogadoService;
import com.brain.help.utils.ImageFileWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FotografiaService {

	private final FotografiaRepository fotografiaRepository;
	private final UsuarioLogadoService usuarioLogadoService;

	@Transactional
	public FotografiaResponseDTO cadastrar(FotografiaDTO dto) throws Exception {
		Dependente dependenteLogado = usuarioLogadoService.getDependenteLogado();
		Fotografia entity = toEntity(dto, dependenteLogado);
		Fotografia salva = fotografiaRepository.save(entity);

		return toDTO(salva);
	}

	@Transactional(readOnly = true)
	public List<FotografiaResponseDTO> buscarTodosDoLogado() {
		Long dependenteId = usuarioLogadoService.getDependenteIdLogado();
		return fotografiaRepository.findByDependenteIdOrderByDataDesc(dependenteId)
				.stream()
				.map(this::toDTO)
				.toList();
	}

	@Transactional(readOnly = true)
	public FotografiaResponseDTO buscarPorId(Long id) {
		Fotografia entity = buscarFotografiaDoDependente(id);
		return toDTO(entity);
	}

	@Transactional
	public FotografiaResponseDTO editar(Long id, FotografiaDTO dto) throws Exception {
		Fotografia entity = buscarFotografiaDoDependente(id);

		entity.setLugar(dto.getLugar());
		entity.setData(dto.getData());
		entity.setLembrancas(dto.getLembrancas());

		if (dto.getFoto() != null && !dto.getFoto().isBlank()) {
			entity.setFoto(ImageFileWriter.saveImageToFolder(entity.getFoto(), dto.getFoto()));
		}

		Fotografia atualizada = fotografiaRepository.save(entity);
		return toDTO(atualizada);
	}

	@Transactional
	public void deletar(Long id) {
		Fotografia entity = buscarFotografiaDoDependente(id);
		fotografiaRepository.delete(entity);
	}

	private Fotografia buscarFotografiaDoDependente(Long fotografiaId) {
		Long dependenteId = usuarioLogadoService.getDependenteIdLogado();
		return fotografiaRepository.findByIdAndDependenteId(fotografiaId, dependenteId)
				.orElseThrow(() -> new NotFoundException("Fotografia não encontrada"));
	}

	private Fotografia toEntity(FotografiaDTO dto, Dependente dependente) throws Exception {
		Fotografia entity = new Fotografia();

		entity.setLugar(dto.getLugar());
		entity.setData(dto.getData());
		entity.setLembrancas(dto.getLembrancas());
		entity.setDependente(dependente);

		if (dto.getFoto() != null && !dto.getFoto().isBlank()) {
			entity.setFoto(ImageFileWriter.saveImageToFolder(null, dto.getFoto()));
		}

		return entity;
	}

	private FotografiaResponseDTO toDTO(Fotografia entity) {
		FotografiaResponseDTO dto = new FotografiaResponseDTO();

		dto.setId(entity.getId());
		dto.setLugar(entity.getLugar());
		dto.setData(entity.getData());
		dto.setLembrancas(entity.getLembrancas());
		dto.setFoto(entity.getFoto());

		return dto;
	}
}
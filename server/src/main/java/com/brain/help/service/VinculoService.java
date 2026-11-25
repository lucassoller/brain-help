package com.brain.help.service;

import com.brain.help.dto.vinculo.VinculoDTO;
import com.brain.help.dto.vinculo.VinculoResponseDTO;
import com.brain.help.entity.Dependente;
import com.brain.help.entity.Vinculo;
import com.brain.help.exception.NotFoundException;
import com.brain.help.repository.VinculoRepository;
import com.brain.help.security.UsuarioLogadoService;
import com.brain.help.utils.ImageFileWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VinculoService {

	private final VinculoRepository vinculoRepository;
	private final UsuarioLogadoService usuarioLogadoService;

	@Transactional
	public VinculoResponseDTO cadastrar(VinculoDTO dto) throws Exception {
		Dependente dependenteLogado = usuarioLogadoService.getDependenteLogado();

		Vinculo entity = toEntity(dto, dependenteLogado);
		Vinculo salvo = vinculoRepository.save(entity);

		return toDTO(salvo);
	}

	@Transactional(readOnly = true)
	public List<VinculoResponseDTO> buscarVinculosDoLogado() {
		Dependente dependenteLogado = usuarioLogadoService.getDependenteLogado();
		return vinculoRepository.findByDependenteIdOrderByNomeAsc(dependenteLogado.getId())
				.stream()
				.map(this::toDTO)
				.toList();
	}

	@Transactional(readOnly = true)
	public VinculoResponseDTO buscarPorId(Long id) {
		Vinculo entity = buscarVinculoPorId(id);
		return toDTO(entity);
	}

	@Transactional(readOnly = true)
	public List<VinculoResponseDTO> buscarPorNome(String nome) {
		if (nome == null || nome.isBlank()) {
			return buscarVinculosDoLogado();
		}

		Dependente dependenteLogado = usuarioLogadoService.getDependenteLogado();
		return vinculoRepository.buscarPorDependenteENome(dependenteLogado.getId(), nome.trim())
				.stream()
				.map(this::toDTO)
				.toList();
	}

	@Transactional(readOnly = true)
	public List<VinculoResponseDTO> buscarPorTipoVinculo(String vinculo) {
		if (vinculo == null || vinculo.isBlank()) {
			return buscarVinculosDoLogado();
		}

		Dependente dependenteLogado = usuarioLogadoService.getDependenteLogado();
		return vinculoRepository
				.findByDependenteIdAndVinculoContainingIgnoreCaseOrderByNomeAsc(dependenteLogado.getId(), vinculo.trim())
				.stream()
				.map(this::toDTO)
				.toList();
	}

	@Transactional
	public VinculoResponseDTO editar(Long id, VinculoDTO dto) throws Exception {
		Vinculo entity = buscarVinculoPorId(id);

		entity.setNome(dto.getNome());
		entity.setSobrenome(dto.getSobrenome());
		entity.setTelefone(dto.getTelefone());
		entity.setVinculo(dto.getVinculo());
		entity.setSexo(dto.getSexo());
		entity.setIdade(dto.getIdade());
		entity.setLembrancas(dto.getLembrancas());

		if (dto.getFoto() != null && !dto.getFoto().isBlank()) {
			entity.setFoto(ImageFileWriter.saveImageToFolder(entity.getFoto(), dto.getFoto()));
		}

		Vinculo atualizado = vinculoRepository.save(entity);
		return toDTO(atualizado);
	}

	@Transactional
	public void deletar(Long id) {
		Vinculo entity = buscarVinculoPorId(id);
		vinculoRepository.delete(entity);
	}

	private Vinculo buscarVinculoPorId(Long vinculoId) {
		return vinculoRepository.findById(vinculoId)
				.orElseThrow(() -> new NotFoundException("Vínculo não encontrado"));
	}

	private Vinculo toEntity(VinculoDTO dto, Dependente dependente) throws Exception {
		Vinculo entity = new Vinculo();

		entity.setNome(dto.getNome());
		entity.setSobrenome(dto.getSobrenome());
		entity.setTelefone(dto.getTelefone());
		entity.setVinculo(dto.getVinculo());
		entity.setSexo(dto.getSexo());
		entity.setIdade(dto.getIdade());
		entity.setLembrancas(dto.getLembrancas());
		entity.setDependente(dependente);

		if (dto.getFoto() != null && !dto.getFoto().isBlank()) {
			entity.setFoto(ImageFileWriter.saveImageToFolder(null, dto.getFoto()));
		}

		return entity;
	}

	private VinculoResponseDTO toDTO(Vinculo entity) {
		VinculoResponseDTO dto = new VinculoResponseDTO();

		dto.setId(entity.getId());
		dto.setNome(entity.getNome());
		dto.setSobrenome(entity.getSobrenome());
		dto.setTelefone(entity.getTelefone());
		dto.setVinculo(entity.getVinculo());
		dto.setSexo(entity.getSexo());
		dto.setIdade(entity.getIdade());
		dto.setLembrancas(entity.getLembrancas());
		dto.setFoto(entity.getFoto());
		return dto;
	}
}
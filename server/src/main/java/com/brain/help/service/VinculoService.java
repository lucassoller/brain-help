package com.brain.help.service;

import com.brain.help.dto.vinculo.VinculoDTO;
import com.brain.help.dto.vinculo.VinculoResponseDTO;
import com.brain.help.entity.Dependente;
import com.brain.help.entity.Endereco;
import com.brain.help.entity.Vinculo;
import com.brain.help.exception.NotFoundException;
import com.brain.help.mapper.EnderecoMapper;
import com.brain.help.repository.EnderecoRepository;
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
	private final EnderecoRepository enderecoRepository;
	private final EnderecoMapper enderecoMapper;
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
		Long dependenteId = usuarioLogadoService.getDependenteIdLogado();
		return vinculoRepository.findByDependenteIdOrderByNomeAsc(dependenteId)
				.stream()
				.map(this::toDTO)
				.toList();
	}

	@Transactional(readOnly = true)
	public VinculoResponseDTO buscarPorId(Long id) {
		Long dependenteId = usuarioLogadoService.getDependenteIdLogado();
		Vinculo entity = buscarVinculoDoDependente(id, dependenteId);
		return toDTO(entity);
	}

	@Transactional(readOnly = true)
	public List<VinculoResponseDTO> buscarPorNome(String nome) {
		if (nome == null || nome.isBlank()) {
			return buscarVinculosDoLogado();
		}

		Long dependenteId = usuarioLogadoService.getDependenteIdLogado();
		return vinculoRepository.buscarPorDependenteENome(dependenteId, nome.trim())
				.stream()
				.map(this::toDTO)
				.toList();
	}

	@Transactional(readOnly = true)
	public List<VinculoResponseDTO> buscarPorTipoVinculo(String vinculo) {
		if (vinculo == null || vinculo.isBlank()) {
			return buscarVinculosDoLogado();
		}

		Long dependenteId = usuarioLogadoService.getDependenteIdLogado();
		return vinculoRepository
				.findByDependenteIdAndVinculoContainingIgnoreCaseOrderByNomeAsc(dependenteId, vinculo.trim())
				.stream()
				.map(this::toDTO)
				.toList();
	}

	@Transactional
	public VinculoResponseDTO editar(Long id, VinculoDTO dto) throws Exception {
		Dependente dependenteLogado = usuarioLogadoService.getDependenteLogado();
		Vinculo entity = buscarVinculoDoDependente(id, dependenteLogado.getId());

		entity.setNome(dto.getNome());
		entity.setSobrenome(dto.getSobrenome());
		entity.setTelefone(dto.getTelefone());
		entity.setVinculo(dto.getVinculo());
		entity.setSexo(dto.getSexo());
		entity.setIdade(dto.getIdade());
		entity.setLembrancas(dto.getLembrancas());
		entity.setEndereco(resolverEndereco(dto, dependenteLogado, entity.getEndereco()));

		if (dto.getFoto() != null && !dto.getFoto().isBlank()) {
			entity.setFoto(ImageFileWriter.saveImageToFolder(entity.getFoto(), dto.getFoto()));
		}

		Vinculo atualizado = vinculoRepository.save(entity);
		return toDTO(atualizado);
	}

	@Transactional
	public void deletar(Long id) {
		Long dependenteId = usuarioLogadoService.getDependenteIdLogado();
		Vinculo entity = buscarVinculoDoDependente(id, dependenteId);
		vinculoRepository.delete(entity);
	}

	private Vinculo buscarVinculoDoDependente(Long vinculoId, Long dependenteId) {
		return vinculoRepository.findByIdAndDependenteId(vinculoId, dependenteId)
				.orElseThrow(() -> new NotFoundException("Vínculo não encontrado"));
	}

	private Endereco resolverEndereco(VinculoDTO dto, Dependente dependente, Endereco enderecoAtual) throws Exception {
		if (dto.getEnderecoId() != null) {
			return enderecoRepository.findByIdAndDependenteId(dto.getEnderecoId(), dependente.getId())
					.orElseThrow(() -> new NotFoundException("Endereço não encontrado"));
		}

		if (dto.getEndereco() != null) {
			if (enderecoAtual != null && !enderecoAtual.isPrincipal()) {
				enderecoMapper.atualizarEntity(enderecoAtual, dto.getEndereco());
				if (enderecoAtual.getTitulo() == null || enderecoAtual.getTitulo().isBlank()) {
					enderecoAtual.setTitulo("Casa de " + dto.getNome());
				}
				return enderecoRepository.save(enderecoAtual);
			}

			Endereco novoEndereco = enderecoMapper.toEntity(dto.getEndereco(), dependente);
			novoEndereco.setPrincipal(false);
			if (novoEndereco.getTitulo() == null || novoEndereco.getTitulo().isBlank()) {
				novoEndereco.setTitulo("Casa de " + dto.getNome());
			}
			return enderecoRepository.save(novoEndereco);
		}

		return null;
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
		entity.setEndereco(resolverEndereco(dto, dependente, null));
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
		dto.setEndereco(enderecoMapper.toDTO(entity.getEndereco()));
		return dto;
	}
}
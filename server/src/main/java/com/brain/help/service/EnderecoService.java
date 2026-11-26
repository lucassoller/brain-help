package com.brain.help.service;

import com.brain.help.dto.endereco.EnderecoDTO;
import com.brain.help.dto.endereco.EnderecoResponseDTO;
import com.brain.help.entity.Dependente;
import com.brain.help.entity.Endereco;
import com.brain.help.exception.BusinessException;
import com.brain.help.exception.NotFoundException;
import com.brain.help.mapper.EnderecoMapper;
import com.brain.help.repository.EnderecoRepository;
import com.brain.help.repository.VinculoRepository;
import com.brain.help.security.UsuarioLogadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnderecoService {

	private final EnderecoRepository enderecoRepository;
	private final VinculoRepository vinculoRepository;
	private final UsuarioLogadoService usuarioLogadoService;
	private final EnderecoMapper enderecoMapper;

	@Transactional
	public EnderecoResponseDTO salvarOuEditarPrincipal(EnderecoDTO dto) throws Exception {
		Dependente dependenteLogado = usuarioLogadoService.getDependenteLogado();

		Endereco entity = enderecoRepository.findByDependenteIdAndPrincipalTrue(dependenteLogado.getId())
				.orElseGet(() -> {
					Endereco novo = new Endereco();
					novo.setDependente(dependenteLogado);
					return novo;
				});

		enderecoMapper.atualizarEntity(entity, dto);
		entity.setPrincipal(true);

		if (entity.getTitulo() == null || entity.getTitulo().isBlank()) {
			entity.setTitulo("Minha Casa");
		}

		Endereco salvo = enderecoRepository.save(entity);
		return enderecoMapper.toDTO(salvo);
	}

	@Transactional(readOnly = true)
	public EnderecoResponseDTO buscarPrincipalDoLogado() {
		Long dependenteId = usuarioLogadoService.getDependenteIdLogado();
		return enderecoRepository.findByDependenteIdAndPrincipalTrue(dependenteId)
				.map(enderecoMapper::toDTO)
				.orElse(null);
	}

	@Transactional
	public EnderecoResponseDTO cadastrar(EnderecoDTO dto) throws Exception {
		Dependente dependenteLogado = usuarioLogadoService.getDependenteLogado();

		Endereco entity = enderecoMapper.toEntity(dto, dependenteLogado);
		entity.setPrincipal(false);

		Endereco salvo = enderecoRepository.save(entity);
		return enderecoMapper.toDTO(salvo);
	}

	@Transactional(readOnly = true)
	public List<EnderecoResponseDTO> buscarTodosDoLogado() {
		Long dependenteId = usuarioLogadoService.getDependenteIdLogado();
		return enderecoRepository.findByDependenteIdOrderByPrincipalDescTituloAsc(dependenteId)
				.stream()
				.map(enderecoMapper::toDTO)
				.toList();
	}

	@Transactional(readOnly = true)
	public EnderecoResponseDTO buscarPorId(Long id) {
		Long dependenteId = usuarioLogadoService.getDependenteIdLogado();
		Endereco entity = buscarEnderecoDoDependente(id, dependenteId);
		return enderecoMapper.toDTO(entity);
	}

	@Transactional
	public EnderecoResponseDTO editar(Long id, EnderecoDTO dto) throws Exception {
		Long dependenteId = usuarioLogadoService.getDependenteIdLogado();
		Endereco entity = buscarEnderecoDoDependente(id, dependenteId);

		if (entity.isPrincipal()) {
			throw new BusinessException("Use a rota de endereço principal para editar sua residência");
		}

		enderecoMapper.atualizarEntity(entity, dto);
		Endereco atualizado = enderecoRepository.save(entity);
		return enderecoMapper.toDTO(atualizado);
	}

	@Transactional
	public void deletar(Long id) {
		Long dependenteId = usuarioLogadoService.getDependenteIdLogado();
		Endereco entity = buscarEnderecoDoDependente(id, dependenteId);

		if (entity.isPrincipal()) {
			throw new BusinessException("Não é possível excluir o endereço principal");
		}

		if (vinculoRepository.existsByEnderecoId(id)) {
			throw new BusinessException("Não é possível excluir um endereço associado a um vínculo");
		}

		enderecoRepository.delete(entity);
	}

	private Endereco buscarEnderecoDoDependente(Long enderecoId, Long dependenteId) {
		return enderecoRepository.findByIdAndDependenteId(enderecoId, dependenteId)
				.orElseThrow(() -> new NotFoundException("Endereço não encontrado"));
	}
}
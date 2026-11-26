package com.brain.help.service;

import com.brain.help.dto.endereco.EnderecoDTO;
import com.brain.help.dto.endereco.EnderecoResponseDTO;
import com.brain.help.entity.Dependente;
import com.brain.help.entity.Endereco;
import com.brain.help.exception.BusinessException;
import com.brain.help.exception.NotFoundException;
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
public class EnderecoService {

	private final EnderecoRepository enderecoRepository;
	private final VinculoRepository vinculoRepository;
	private final UsuarioLogadoService usuarioLogadoService;

	@Transactional
	public EnderecoResponseDTO cadastrar(EnderecoDTO dto) throws Exception {
		Dependente dependenteLogado = usuarioLogadoService.getDependenteLogado();

		if (dto.isPrincipal()) {
			enderecoRepository.desmarcarPrincipalDoDependente(dependenteLogado.getId());
		}

		Endereco entity = toEntity(dto, dependenteLogado);
		Endereco salvo = enderecoRepository.save(entity);
		return toDTO(salvo);
	}

	@Transactional(readOnly = true)
	public List<EnderecoResponseDTO> buscarTodosDoLogado() {
		Dependente dependente = usuarioLogadoService.getDependenteLogado();
		return enderecoRepository.findByDependenteIdOrderByPrincipalDescTituloAsc(dependente.getId())
				.stream()
				.map(this::toDTO)
				.toList();
	}

	@Transactional(readOnly = true)
	public EnderecoResponseDTO buscarPorId(Long id) {
		Dependente dependente = usuarioLogadoService.getDependenteLogado();
		Endereco entity = buscarEnderecoDoDependente(id, dependente.getId());
		return toDTO(entity);
	}

	@Transactional(readOnly = true)
	public EnderecoResponseDTO buscarPrincipalDoLogado() {
		Dependente dependente = usuarioLogadoService.getDependenteLogado();
		Endereco entity = enderecoRepository.findByDependenteIdAndPrincipalTrue(dependente.getId())
				.orElseThrow(() -> new NotFoundException("Endereço principal não cadastrado"));
		return toDTO(entity);
	}

	@Transactional
	public EnderecoResponseDTO editar(Long id, EnderecoDTO dto) throws Exception {
		Dependente dependente = usuarioLogadoService.getDependenteLogado();
		Endereco entity = buscarEnderecoDoDependente(id, dependente.getId());

		if (dto.isPrincipal() && !entity.isPrincipal()) {
			enderecoRepository.desmarcarPrincipalDoDependente(dependente.getId());
		}

		entity.setTitulo(dto.getTitulo());
		entity.setLogradouro(dto.getLogradouro());
		entity.setNumero(dto.getNumero());
		entity.setBairro(dto.getBairro());
		entity.setCidade(dto.getCidade());
		entity.setEstado(dto.getEstado());
		entity.setCep(dto.getCep());
		entity.setPrincipal(dto.isPrincipal());

		if (dto.getFoto() != null && !dto.getFoto().isBlank()) {
			entity.setFoto(ImageFileWriter.saveImageToFolder(entity.getFoto(), dto.getFoto()));
		}

		Endereco atualizado = enderecoRepository.save(entity);
		return toDTO(atualizado);
	}

	@Transactional
	public void deletar(Long id) {
		Dependente dependente = usuarioLogadoService.getDependenteLogado();
		Endereco entity = buscarEnderecoDoDependente(id, dependente.getId());

		if (vinculoRepository.existsByEnderecoId(id)) {
			throw new BusinessException("Não é possível excluir um endereço associado a um vínculo");
		}

		enderecoRepository.delete(entity);
	}

	private Endereco buscarEnderecoDoDependente(Long enderecoId, Long dependenteId) {
		return enderecoRepository.findByIdAndDependenteId(enderecoId, dependenteId)
				.orElseThrow(() -> new NotFoundException("Endereço não encontrado"));
	}

	private Endereco toEntity(EnderecoDTO dto, Dependente dependente) throws Exception {
		Endereco entity = new Endereco();

		entity.setTitulo(dto.getTitulo());
		entity.setLogradouro(dto.getLogradouro());
		entity.setNumero(dto.getNumero());
		entity.setBairro(dto.getBairro());
		entity.setCidade(dto.getCidade());
		entity.setEstado(dto.getEstado());
		entity.setCep(dto.getCep());
		entity.setPrincipal(dto.isPrincipal());
		entity.setDependente(dependente);

		if (dto.getFoto() != null && !dto.getFoto().isBlank()) {
			entity.setFoto(ImageFileWriter.saveImageToFolder(null, dto.getFoto()));
		}

		return entity;
	}

	private EnderecoResponseDTO toDTO(Endereco entity) {
		EnderecoResponseDTO dto = new EnderecoResponseDTO();

		dto.setId(entity.getId());
		dto.setTitulo(entity.getTitulo());
		dto.setLogradouro(entity.getLogradouro());
		dto.setNumero(entity.getNumero());
		dto.setBairro(entity.getBairro());
		dto.setCidade(entity.getCidade());
		dto.setEstado(entity.getEstado());
		dto.setCep(entity.getCep());
		dto.setFoto(entity.getFoto());
		dto.setPrincipal(entity.isPrincipal());

		return dto;
	}
}
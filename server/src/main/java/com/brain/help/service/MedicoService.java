package com.brain.help.service;

import com.brain.help.dto.medico.MedicoDTO;
import com.brain.help.dto.medico.MedicoResponseDTO;
import com.brain.help.dto.senha.MudancaSenhaDTO;
import com.brain.help.entity.Medico;
import com.brain.help.exception.BusinessException;
import com.brain.help.exception.ConflictException;
import com.brain.help.exception.NotFoundException;
import com.brain.help.mapper.EnderecoMapper;
import com.brain.help.repository.MedicoRepository;
import com.brain.help.security.UsuarioLogadoService;
import com.brain.help.utils.ImageFileWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MedicoService {

	private final MedicoRepository medicoRepository;
	private final EnderecoMapper enderecoMapper;
	private final UsuarioLogadoService usuarioLogadoService;
	private final PasswordEncoder passwordEncoder;

	@Transactional
	public MedicoResponseDTO criar(MedicoDTO dto) throws Exception {
		if (dto.getSenha() == null || dto.getSenha().isBlank()) {
			throw new BusinessException("A senha é obrigatória no cadastro");
		}

		validarSenhas(dto);
		validarDuplicidadeCreate(dto);

		Medico entity = toEntity(dto);
		Medico salvo = medicoRepository.save(entity);

		return toDTO(salvo);
	}

	@Transactional(readOnly = true)
	public List<MedicoResponseDTO> buscarTodos() {
		return medicoRepository.findAll()
				.stream()
				.map(this::toDTO)
				.toList();
	}

	@Transactional(readOnly = true)
	public MedicoResponseDTO buscarPerfilLogado() {
		Medico entity = usuarioLogadoService.getMedicoLogado();
		return toDTO(entity);
	}

	@Transactional(readOnly = true)
	public MedicoResponseDTO buscarPorId(Long id) {
		Medico entity = buscarMedicoPorId(id);
		return toDTO(entity);
	}

	@Transactional(readOnly = true)
	public MedicoResponseDTO buscarPorEmail(String email) {
		Medico entity = medicoRepository.findByEmail(email)
				.orElseThrow(() -> new NotFoundException("Médico não encontrado com o e-mail: " + email));
		return toDTO(entity);
	}

	@Transactional
	public MedicoResponseDTO atualizarPerfilLogado(MedicoDTO dto) throws Exception {
		Medico entity = usuarioLogadoService.getMedicoLogado();
		return executarAtualizacao(entity, dto);
	}

	@Transactional
	public MedicoResponseDTO atualizar(Long id, MedicoDTO dto) throws Exception {
		Medico medicoLogado = usuarioLogadoService.getMedicoLogado();
		if (!Objects.equals(medicoLogado.getId(), id)) {
			throw new BusinessException("Você não tem permissão para alterar o perfil de outro médico");
		}
		return executarAtualizacao(medicoLogado, dto);
	}

	@Transactional
	public MedicoResponseDTO atualizarSenhaLogado(MudancaSenhaDTO dto) {
		Medico entity = usuarioLogadoService.getMedicoLogado();
		return executarAtualizacaoSenha(entity, dto);
	}

	@Transactional
	public void deletarContaLogada() {
		Medico entity = usuarioLogadoService.getMedicoLogado();
		medicoRepository.delete(entity);
	}

	@Transactional
	public void deletar(Long id) {
		Medico medicoLogado = usuarioLogadoService.getMedicoLogado();
		if (!Objects.equals(medicoLogado.getId(), id)) {
			throw new BusinessException("Você não tem permissão para excluir o perfil de outro médico");
		}
		medicoRepository.delete(medicoLogado);
	}

	private MedicoResponseDTO executarAtualizacao(Medico entity, MedicoDTO dto) throws Exception {
		validarDuplicidadeUpdate(dto, entity.getId());

		entity.setNome(dto.getNome());
		entity.setSobrenome(dto.getSobrenome());
		entity.setEmail(dto.getEmail());
		entity.setTelefone(dto.getTelefone());
		entity.setEspecializacao(dto.getEspecializacao());

		if (dto.getEnderecoConsultorio() != null) {
			entity.setEnderecoConsultorio(enderecoMapper.toConsultorioEntity(dto.getEnderecoConsultorio()));
		}

		if (dto.getFoto() != null && !dto.getFoto().isBlank()) {
			entity.setFoto(ImageFileWriter.saveImageToFolder(entity.getFoto(), dto.getFoto()));
		}

		Medico atualizado = medicoRepository.save(entity);
		return toDTO(atualizado);
	}

	private MedicoResponseDTO executarAtualizacaoSenha(Medico entity, MudancaSenhaDTO dto) {
		validarSenhas(dto, entity.getSenha());

		entity.setSenha(passwordEncoder.encode(dto.getNovaSenha()));
		Medico atualizado = medicoRepository.save(entity);
		return toDTO(atualizado);
	}

	private void validarSenhas(MedicoDTO dto) {
		if (!Objects.equals(dto.getSenha(), dto.getConfirmarSenha())) {
			throw new BusinessException("As senhas não coincidem");
		}
	}

	private void validarSenhas(MudancaSenhaDTO dto, String senhaHash) {
		if (!passwordEncoder.matches(dto.getSenhaAtual(), senhaHash)) {
			throw new BusinessException("Senha atual inválida");
		}

		if (!Objects.equals(dto.getNovaSenha(), dto.getConfirmarSenha())) {
			throw new BusinessException("As senhas não coincidem");
		}

		if (Objects.equals(dto.getSenhaAtual(), dto.getNovaSenha())) {
			throw new BusinessException("A nova senha deve ser diferente da senha atual");
		}
	}

	private void validarDuplicidadeCreate(MedicoDTO dto) {
		if (medicoRepository.existsByEmail(dto.getEmail())) {
			throw new ConflictException("Email já cadastrado");
		}
	}

	private void validarDuplicidadeUpdate(MedicoDTO dto, Long id) {
		if (medicoRepository.existsByEmailAndIdNot(dto.getEmail(), id)) {
			throw new ConflictException("Email já cadastrado");
		}
	}

	private Medico buscarMedicoPorId(Long id) {
		return medicoRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Médico não encontrado"));
	}

	private Medico toEntity(MedicoDTO dto) throws Exception {
		Medico entity = new Medico();

		entity.setNome(dto.getNome());
		entity.setSobrenome(dto.getSobrenome());
		entity.setEmail(dto.getEmail());
		entity.setTelefone(dto.getTelefone());
		entity.setEspecializacao(dto.getEspecializacao());
		entity.setSenha(passwordEncoder.encode(dto.getSenha()));
		entity.setEnderecoConsultorio(enderecoMapper.toConsultorioEntity(dto.getEnderecoConsultorio()));
		entity.setGoogle(false);

		if (dto.getFoto() != null && !dto.getFoto().isBlank()) {
			entity.setFoto(ImageFileWriter.saveImageToFolder(null, dto.getFoto()));
		}

		return entity;
	}

	public MedicoResponseDTO toDTO(Medico entity) {
		if (entity == null) {
			return null;
		}

		MedicoResponseDTO dto = new MedicoResponseDTO();

		dto.setId(entity.getId());
		dto.setNome(entity.getNome());
		dto.setSobrenome(entity.getSobrenome());
		dto.setEmail(entity.getEmail());
		dto.setTelefone(entity.getTelefone());
		dto.setEspecializacao(entity.getEspecializacao());
		dto.setFoto(entity.getFoto());
		dto.setEnderecoConsultorio(enderecoMapper.toConsultorioDTO(entity.getEnderecoConsultorio()));
		dto.setGoogle(entity.isGoogle());

		return dto;
	}
}
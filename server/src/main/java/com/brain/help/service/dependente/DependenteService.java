package com.brain.help.service.dependente;

import com.brain.help.dto.dependente.DependenteDTO;
import com.brain.help.dto.dependente.DependenteResponseDTO;
import com.brain.help.entity.Dependente;
import com.brain.help.exception.BusinessException;
import com.brain.help.exception.ConflictException;
import com.brain.help.exception.NotFoundException;
import com.brain.help.repository.DependenteRepository;
import com.brain.help.security.UsuarioLogadoService;
import com.brain.help.utils.ImageFileWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DependenteService {

    private final DependenteRepository dependenteRepository;
    private final UsuarioLogadoService usuarioLogadoService;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public DependenteResponseDTO criar(DependenteDTO dto) throws Exception {
        if (dto.getSenha() == null || dto.getSenha().isBlank()) {
            throw new BusinessException("A senha é obrigatória no cadastro");
        }

        validarSenhas(dto);
        validarDuplicidadeCreate(dto);

        Dependente entity = toEntity(dto);
        Dependente salvo = dependenteRepository.save(entity);

        return toDTO(salvo);
    }

    @Transactional(readOnly = true)
    public DependenteResponseDTO buscarPerfilLogado() {
        Dependente entity = usuarioLogadoService.getDependenteLogado();
        return toDTO(entity);
    }

    @Transactional(readOnly = true)
    public DependenteResponseDTO buscarPorId(Long id) {
        Dependente entity = buscarDependentePorId(id);
        return toDTO(entity);
    }

    @Transactional(readOnly = true)
    public DependenteResponseDTO buscarPorEmail(String email) {
        Dependente entity = dependenteRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Dependente não encontrado com o e-mail: " + email));
        return toDTO(entity);
    }

    @Transactional
    public DependenteResponseDTO atualizarPerfilLogado(DependenteDTO dto) throws Exception {
        Dependente entity = usuarioLogadoService.getDependenteLogado();
        return executarAtualizacao(entity, dto);
    }

    @Transactional
    public DependenteResponseDTO atualizar(Long id, DependenteDTO dto) throws Exception {
        Dependente entity = buscarDependentePorId(id);
        return executarAtualizacao(entity, dto);
    }

    @Transactional
    public void deletarContaLogada() {
        Dependente entity = usuarioLogadoService.getDependenteLogado();
        dependenteRepository.delete(entity);
    }

    @Transactional
    public void deletar(Long id) {
        Dependente entity = buscarDependentePorId(id);
        dependenteRepository.delete(entity);
    }

    private DependenteResponseDTO executarAtualizacao(Dependente entity, DependenteDTO dto) throws Exception {
        validarDuplicidadeUpdate(dto, entity.getId());

        entity.setNome(dto.getNome());
        entity.setSobrenome(dto.getSobrenome());
        entity.setEmail(dto.getEmail());
        entity.setTelefone(dto.getTelefone());
        entity.setDataNascimento(dto.getDataNascimento());
        entity.setDataDiagnostico(dto.getDataDiagnostico());
        entity.setSexo(dto.getSexo());
        entity.setEstagioAlzheimer(dto.getEstagioAlzheimer());
        entity.setChaveSeguranca(dto.getChaveSeguranca());

        if (dto.getFoto() != null && !dto.getFoto().isBlank()) {
            entity.setFoto(ImageFileWriter.saveImageToFolder(entity.getFoto(), dto.getFoto()));
        }

        Dependente atualizado = dependenteRepository.save(entity);
        return toDTO(atualizado);
    }

    private void validarSenhas(DependenteDTO dto) {
        if (!Objects.equals(dto.getSenha(), dto.getConfirmarSenha())) {
            throw new BusinessException("As senhas não coincidem");
        }
    }

    private void validarDuplicidadeCreate(DependenteDTO dto) {
        if (dependenteRepository.existsByEmail(dto.getEmail())) {
            throw new ConflictException("Email já cadastrado");
        }
    }

    private void validarDuplicidadeUpdate(DependenteDTO dto, Long id) {
        if (dependenteRepository.existsByEmailAndIdNot(dto.getEmail(), id)) {
            throw new ConflictException("Email já cadastrado");
        }
    }

    private Dependente buscarDependentePorId(Long id) {
        return dependenteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Dependente não encontrado"));
    }

    private Dependente toEntity(DependenteDTO dto) throws Exception {
        Dependente entity = new Dependente();

        entity.setNome(dto.getNome());
        entity.setSobrenome(dto.getSobrenome());
        entity.setEmail(dto.getEmail());
        entity.setTelefone(dto.getTelefone());
        entity.setSenhaHash(passwordEncoder.encode(dto.getSenha()));
        entity.setDataNascimento(dto.getDataNascimento());
        entity.setDataDiagnostico(dto.getDataDiagnostico());
        entity.setSexo(dto.getSexo());
        entity.setEstagioAlzheimer(dto.getEstagioAlzheimer());
        entity.setChaveSeguranca(dto.getChaveSeguranca());
        entity.setGoogle(false);

        if (dto.getFoto() != null && !dto.getFoto().isBlank()) {
            entity.setFoto(ImageFileWriter.saveImageToFolder(null, dto.getFoto()));
        }

        return entity;
    }

    private DependenteResponseDTO toDTO(Dependente entity) {
        DependenteResponseDTO dto = new DependenteResponseDTO();

        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setSobrenome(entity.getSobrenome());
        dto.setEmail(entity.getEmail());
        dto.setTelefone(entity.getTelefone());
        dto.setDataNascimento(entity.getDataNascimento());
        dto.setDataDiagnostico(entity.getDataDiagnostico());
        dto.setSexo(entity.getSexo());
        dto.setEstagioAlzheimer(entity.getEstagioAlzheimer());
        dto.setDesempenhoAtual(entity.getDesempenhoAtual());
        dto.setFoto(entity.getFoto());
        dto.setGoogle(entity.isGoogle());

        return dto;
    }
}
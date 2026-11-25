package com.brain.help.service.dependente;

import com.brain.help.dto.dependente.DependenteDTO;
import com.brain.help.dto.dependente.DependenteResponseDTO;
import com.brain.help.dto.senha.MudancaSenhaDTO;
import com.brain.help.entity.Dependente;
import com.brain.help.entity.Medico;
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

import java.util.List;
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
    public List<DependenteResponseDTO> buscarTodos() {
        return dependenteRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public DependenteResponseDTO buscarPerfilLogado() {
        return toDTO(usuarioLogadoService.getDependenteLogado());
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
    public DependenteResponseDTO atualizarPorId(Long id, DependenteDTO dto) throws Exception {
        Dependente entity = buscarDependentePorId(id);
        return executarAtualizacao(entity, dto);
    }

    @Transactional
    public DependenteResponseDTO atualizarSenhaLogado(MudancaSenhaDTO dto) {
        Dependente entity = usuarioLogadoService.getDependenteLogado();
        return executarAtualizacaoSenhaLogado(entity, dto);
    }

    @Transactional
    public void vincularAoMedicoLogado(Long dependenteId) {
        Medico medicoLogado = usuarioLogadoService.getMedicoLogado();
        Dependente dependente = buscarDependentePorId(dependenteId);

        if (dependente.getMedico() != null) {
            if (Objects.equals(dependente.getMedico().getId(), medicoLogado.getId())) {
                throw new BusinessException("Este dependente já está vinculado a você");
            }
            throw new ConflictException("Este dependente já está vinculado a outro médico");
        }

        dependente.setMedico(medicoLogado);
        dependenteRepository.save(dependente);
    }

    @Transactional(readOnly = true)
    public List<DependenteResponseDTO> buscarDependentesPorNomeOuEmail(String termo) {
        if (termo == null || termo.trim().length() < 2) {
            throw new BusinessException("Informe pelo menos 2 caracteres para a busca");
        }

        return dependenteRepository.buscarDependentesPorNomeOuEmail(termo.trim())
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<DependenteResponseDTO> buscarTodosVinculadosAoMedicoLogado() {
        Medico medicoLogado = usuarioLogadoService.getMedicoLogado();
        return dependenteRepository.findByMedicoIdOrderByNomeAsc(medicoLogado.getId())
                .stream()
                .map(this::toDTO)
                .toList();
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

    public DependenteResponseDTO executarAtualizacaoSenhaLogado(Dependente entity, MudancaSenhaDTO dto) {
        validarSenhas(dto, entity.getSenhaHash());
        entity.setSenhaHash(passwordEncoder.encode(dto.getNovaSenha()));
        Dependente atualizado = dependenteRepository.save(entity);
        return toDTO(atualizado);
    }

    private void validarSenhas(DependenteDTO dto) {
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
package com.brain.help.service;

import com.brain.help.dto.desempenho.DesempenhoDTO;
import com.brain.help.dto.desempenho.DesempenhoResponseDTO;
import com.brain.help.entity.Dependente;
import com.brain.help.entity.Desempenho;
import com.brain.help.entity.Medico;
import com.brain.help.exception.BusinessException;
import com.brain.help.exception.NotFoundException;
import com.brain.help.repository.DependenteRepository;
import com.brain.help.repository.DesempenhoRepository;
import com.brain.help.security.UsuarioLogadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DesempenhoService {

    private final DesempenhoRepository desempenhoRepository;
    private final DependenteRepository dependenteRepository;
    private final UsuarioLogadoService usuarioLogadoService;

    @Transactional
    public DesempenhoResponseDTO cadastrar(DesempenhoDTO dto) {
        Dependente dependenteLogado = usuarioLogadoService.getDependenteLogado();
        Desempenho entity = toEntity(dto, dependenteLogado);
        Desempenho salvo = desempenhoRepository.save(entity);
        return toDTO(salvo);
    }

    @Transactional(readOnly = true)
    public List<DesempenhoResponseDTO> buscarTodosDoLogado() {
        Long dependenteId = usuarioLogadoService.getDependenteIdLogado();
        return desempenhoRepository.findByDependenteIdOrderByDataRealizacaoDesc(dependenteId)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public DesempenhoResponseDTO buscarPorId(Long id) {
        Desempenho entity = buscarDesempenhoDoDependente(id);
        return toDTO(entity);
    }

    @Transactional(readOnly = true)
    public List<DesempenhoResponseDTO> buscarPorDependenteParaMedico(Long dependenteId) {
        Medico medicoLogado = usuarioLogadoService.getMedicoLogado();
        Dependente dependente = dependenteRepository.findById(dependenteId)
                .orElseThrow(() -> new NotFoundException("Dependente não encontrado"));

        if (dependente.getMedico() == null || !Objects.equals(dependente.getMedico().getId(), medicoLogado.getId())) {
            throw new BusinessException("Este dependente não está vinculado ao seu perfil");
        }

        return desempenhoRepository.findByDependenteIdOrderByDataRealizacaoDesc(dependenteId)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Transactional
    public DesempenhoResponseDTO editar(Long id, DesempenhoDTO dto) {
        Desempenho entity = buscarDesempenhoDoDependente(id);

        entity.setAtividade(dto.getAtividade());
        entity.setPontuacao(dto.getPontuacao());
        if (dto.getDataRealizacao() != null) {
            entity.setDataRealizacao(dto.getDataRealizacao());
        }
        entity.setAvaliacaoDesempenho(dto.getAvaliacaoDesempenho());

        Desempenho atualizado = desempenhoRepository.save(entity);
        return toDTO(atualizado);
    }

    @Transactional
    public void deletar(Long id) {
        Desempenho entity = buscarDesempenhoDoDependente(id);
        desempenhoRepository.delete(entity);
    }

    private Desempenho buscarDesempenhoDoDependente(Long desempenhoId) {
        Long dependenteId = usuarioLogadoService.getDependenteIdLogado();
        return desempenhoRepository.findByIdAndDependenteId(desempenhoId, dependenteId)
                .orElseThrow(() -> new NotFoundException("Registro de desempenho não encontrado"));
    }

    private Desempenho toEntity(DesempenhoDTO dto, Dependente dependente) {
        Desempenho entity = new Desempenho();
        entity.setAtividade(dto.getAtividade());
        entity.setPontuacao(dto.getPontuacao());
        entity.setDataRealizacao(dto.getDataRealizacao() != null ? dto.getDataRealizacao() : LocalDateTime.now());
        entity.setAvaliacaoDesempenho(dto.getAvaliacaoDesempenho());
        entity.setDependente(dependente);
        return entity;
    }

    private DesempenhoResponseDTO toDTO(Desempenho entity) {
        DesempenhoResponseDTO dto = new DesempenhoResponseDTO();
        dto.setId(entity.getId());
        dto.setAtividade(entity.getAtividade());
        dto.setPontuacao(entity.getPontuacao());
        dto.setDataRealizacao(entity.getDataRealizacao());
        dto.setAvaliacaoDesempenho(entity.getAvaliacaoDesempenho());
        return dto;
    }
}
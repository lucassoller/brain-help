package com.brain.help.mapper;

import com.brain.help.dto.endereco.EnderecoDTO;
import com.brain.help.dto.endereco.EnderecoResponseDTO;
import com.brain.help.dto.endereco.EnderecoConsultorioDTO;
import com.brain.help.entity.Dependente;
import com.brain.help.entity.Endereco;
import com.brain.help.entity.EnderecoConsultorio;
import com.brain.help.utils.ImageFileWriter;
import org.springframework.stereotype.Component;

@Component
public class EnderecoMapper {

    public Endereco toEntity(EnderecoDTO dto, Dependente dependente) throws Exception {
        if (dto == null) {
            return null;
        }

        Endereco entity = new Endereco();
        atualizarEntity(entity, dto);
        entity.setDependente(dependente);
        return entity;
    }

    public void atualizarEntity(Endereco entity, EnderecoDTO dto) throws Exception {
        entity.setTitulo(dto.getTitulo());
        entity.setLogradouro(dto.getLogradouro());
        entity.setNumero(dto.getNumero());
        entity.setBairro(dto.getBairro());
        entity.setCidade(dto.getCidade());
        entity.setEstado(dto.getEstado());
        entity.setCep(dto.getCep());

        if (dto.getFoto() != null && !dto.getFoto().isBlank()) {
            entity.setFoto(ImageFileWriter.saveImageToFolder(entity.getFoto(), dto.getFoto()));
        }
    }

    public EnderecoResponseDTO toDTO(Endereco entity) {
        if (entity == null) {
            return null;
        }

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

    public EnderecoConsultorio toConsultorioEntity(EnderecoConsultorioDTO dto) {
        if (dto == null) {
            return null;
        }

        EnderecoConsultorio entity = new EnderecoConsultorio();
        entity.setLogradouro(dto.getLogradouro());
        entity.setNumero(dto.getNumero());
        entity.setComplemento(dto.getComplemento());
        entity.setBairro(dto.getBairro());
        entity.setCidade(dto.getCidade());
        entity.setEstado(dto.getEstado());
        entity.setCep(dto.getCep());
        return entity;
    }

    public EnderecoConsultorioDTO toConsultorioDTO(EnderecoConsultorio entity) {
        if (entity == null) {
            return null;
        }

        EnderecoConsultorioDTO dto = new EnderecoConsultorioDTO();
        dto.setLogradouro(entity.getLogradouro());
        dto.setNumero(entity.getNumero());
        dto.setComplemento(entity.getComplemento());
        dto.setBairro(entity.getBairro());
        dto.setCidade(entity.getCidade());
        dto.setEstado(entity.getEstado());
        dto.setCep(entity.getCep());
        return dto;
    }
}
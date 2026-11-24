package com.brain.help.dto.medico;

import lombok.Data;

@Data
public class MedicoResponseDTO {

    private Long id;
    private String nome;
    private String sobrenome;
    private String email;
    private String telefone;
    private String especializacao;
    private String foto;
    private boolean google;
}
package com.brain.help.dto.vinculo;

import com.brain.help.enums.Sexo;
import lombok.Data;

@Data
public class VinculoResponseDTO {

    private Long id;
    private String nome;
    private String sobrenome;
    private String telefone;
    private String vinculo;
    private Sexo sexo;
    private int idade;
    private String lembrancas;
    private String foto;
}
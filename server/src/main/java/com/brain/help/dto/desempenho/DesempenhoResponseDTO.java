package com.brain.help.dto.desempenho;

import com.brain.help.enums.AvaliacaoDesempenho;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DesempenhoResponseDTO {

    private Long id;
    private String atividade;
    private int pontuacao;
    private LocalDateTime dataRealizacao;
    private AvaliacaoDesempenho avaliacaoDesempenho;
}
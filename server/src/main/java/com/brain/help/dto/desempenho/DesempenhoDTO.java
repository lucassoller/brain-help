package com.brain.help.dto.desempenho;

import com.brain.help.enums.AvaliacaoDesempenho;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DesempenhoDTO {

    @NotBlank(message = "A atividade é obrigatória")
    @Size(max = 100, message = "A atividade deve ter no máximo 100 caracteres")
    private String atividade;

    @NotNull(message = "A pontuação é obrigatória")
    @Min(value = 0, message = "A pontuação não pode ser negativa")
    private Integer pontuacao;

    private LocalDateTime dataRealizacao;

    @NotNull(message = "A avaliação de desempenho é obrigatória")
    private AvaliacaoDesempenho avaliacaoDesempenho;
}
package com.brain.help.dto.tarefa;

import com.brain.help.enums.StatusTarefa;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TarefaResponseDTO {

    private Long id;
    private String tarefa;
    private String descricao;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataFinalizacao;
    private StatusTarefa statusTarefa;
}
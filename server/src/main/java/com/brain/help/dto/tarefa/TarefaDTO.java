package com.brain.help.dto.tarefa;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TarefaDTO {

    @NotBlank(message = "O título da tarefa é obrigatório")
    @Size(max = 200, message = "A tarefa deve ter no máximo 200 caracteres")
    private String tarefa;

    @NotBlank(message = "A descrição é obrigatória")
    @Size(max = 2000, message = "A descrição deve ter no máximo 2000 caracteres")
    private String descricao;
}
package com.brain.help.dto.medicamento;

import com.brain.help.enums.Frequencia;
import com.brain.help.enums.Medida;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class MedicamentoDTO {

    @NotBlank(message = "O nome do medicamento é obrigatório")
    @Size(max = 200, message = "O nome do medicamento deve ter no máximo 200 caracteres")
    private String nomeMedicamento;

    @Size(max = 1000, message = "A função deve ter no máximo 1000 caracteres")
    private String funcao;

    @Size(max = 2000, message = "As contraindicações devem ter no máximo 2000 caracteres")
    private String contraIndicacoes;

    @Size(max = 2000, message = "Os efeitos colaterais devem ter no máximo 2000 caracteres")
    private String efeitosColaterais;

    @NotNull(message = "A quantidade é obrigatória")
    @Min(value = 1, message = "A quantidade deve ser maior que zero")
    private int quantidade;

    @NotNull(message = "A medida é obrigatória")
    private Medida medida;

    @NotNull(message = "A data e horário inicial são obrigatórios")
    private LocalDateTime dataHoraInicial;

    @NotNull(message = "A data final é obrigatória")
    private LocalDate dataFinal;

    private Frequencia frequencia;

    private String foto;
}
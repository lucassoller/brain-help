package com.brain.help.dto.medicamento;

import com.brain.help.enums.Frequencia;
import com.brain.help.enums.Medida;
import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class MedicamentoResponseDTO {

    private Long id;
    private String nomeMedicamento;
    private String funcao;
    private String contraIndicacoes;
    private String efeitosColaterais;
    private int quantidade;
    private Medida medida;
    private LocalDateTime dataHoraInicial;
    private LocalDate dataFinal;
    private Frequencia frequencia;
    private String foto;
}
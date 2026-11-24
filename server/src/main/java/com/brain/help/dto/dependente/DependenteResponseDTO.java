package com.brain.help.dto.dependente;

import com.brain.help.entity.Medico;
import com.brain.help.enums.AvaliacaoDesempenho;
import com.brain.help.enums.EstagioAlzheimer;
import com.brain.help.enums.Sexo;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
public class DependenteResponseDTO {

    private Long id;
    private String nome;
    private String sobrenome;
    private String email;
    private String telefone;
    private LocalDate dataNascimento;
    private Sexo sexo;
    private EstagioAlzheimer estagioAlzheimer;
    private AvaliacaoDesempenho desempenhoAtual;
    private String foto;
    private LocalDate dataDiagnostico;
    private boolean google;
}

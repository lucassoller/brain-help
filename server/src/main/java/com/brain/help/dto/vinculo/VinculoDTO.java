package com.brain.help.dto.vinculo;

import com.brain.help.enums.Sexo;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class VinculoDTO {

    @NotBlank(message = "O nome é obrigatório")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
    private String nome;

    @NotBlank(message = "O sobrenome é obrigatório")
    @Size(max = 100, message = "O sobrenome deve ter no máximo 100 caracteres")
    private String sobrenome;

    @NotBlank(message = "O telefone é obrigatório")
    @Size(max = 20, message = "O telefone deve ter no máximo 20 caracteres")
    private String telefone;

    @NotBlank(message = "O tipo de vínculo é obrigatório")
    @Size(max = 100, message = "O vínculo deve ter no máximo 100 caracteres")
    private String vinculo;

    @NotNull(message = "O sexo é obrigatório")
    private Sexo sexo;

    @NotNull(message = "A idade é obrigatória")
    @Min(value = 0, message = "A idade não pode ser negativa")
    @Max(value = 150, message = "Idade inválida")
    private int idade;

    @Size(max = 2000, message = "As lembranças devem ter no máximo 2000 caracteres")
    private String lembrancas;

    private String foto;
}
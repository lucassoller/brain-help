package com.brain.help.dto.fotografia;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class FotografiaDTO {

    @NotBlank(message = "O lugar é obrigatório")
    @Size(max = 200, message = "O lugar deve ter no máximo 200 caracteres")
    private String lugar;

    private LocalDate data;

    @NotBlank(message = "A descrição das lembranças é obrigatória")
    @Size(max = 2000, message = "As lembranças devem ter no máximo 2000 caracteres")
    private String lembrancas;

    @NotBlank(message = "A foto é obrigatória")
    @Size(max = 2000, message = "A foto deve ter no máximo 2000 caracteres")
    private String foto;
}
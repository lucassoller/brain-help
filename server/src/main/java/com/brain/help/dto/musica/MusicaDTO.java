package com.brain.help.dto.musica;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MusicaDTO {

    @NotBlank(message = "O nome da música é obrigatório")
    @Size(max = 200, message = "O nome da música deve ter no máximo 200 caracteres")
    private String nomeMusica;

    @NotBlank(message = "O cantor é obrigatório")
    @Size(max = 200, message = "O cantor deve ter no máximo 200 caracteres")
    private String cantor;

    @Size(max = 2000, message = "As lembranças devem ter no máximo 2000 caracteres")
    private String lembrancas;

    @NotBlank(message = "A música é obrigatória")
    private String musica;
}
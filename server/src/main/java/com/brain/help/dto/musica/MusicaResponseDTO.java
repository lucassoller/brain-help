package com.brain.help.dto.musica;

import lombok.Data;

@Data
public class MusicaResponseDTO {

    private Long id;
    private String nomeMusica;
    private String cantor;
    private String lembrancas;
    private String musica;
}
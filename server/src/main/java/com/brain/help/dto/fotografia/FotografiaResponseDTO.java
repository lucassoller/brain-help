package com.brain.help.dto.fotografia;

import lombok.Data;

import java.time.LocalDate;

@Data
public class FotografiaResponseDTO {

    private Long id;
    private String lugar;
    private LocalDate data;
    private String lembrancas;
    private String foto;
}
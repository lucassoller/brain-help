package com.brain.help.dto.endereco;

import lombok.Data;

@Data
public class EnderecoResponseDTO {

    private Long id;
    private String titulo;
    private String logradouro;
    private int numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
    private String foto;
    private boolean principal;
}
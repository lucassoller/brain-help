package com.brain.help.dto.endereco;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EnderecoConsultorioDTO {

    @Size(max = 200, message = "O logradouro deve ter no máximo 200 caracteres")
    private String logradouro;

    @Min(value = 1, message = "O número deve ser maior que zero")
    private Integer numero;

    @Size(max = 100, message = "O complemento deve ter no máximo 100 caracteres")
    private String complemento;

    @Size(max = 100, message = "O bairro deve ter no máximo 100 caracteres")
    private String bairro;

    @Size(max = 100, message = "A cidade deve ter no máximo 100 caracteres")
    private String cidade;

    @Size(max = 100, message = "O estado deve ter no máximo 100 caracteres")
    private String estado;

    @Size(max = 9, message = "O CEP deve ter no máximo 9 caracteres")
    private String cep;
}
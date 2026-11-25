package com.brain.help.dto.senha;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RedefinirSenhaDTO {

    @NotBlank(message = "O token é obrigatório")
    private String token;

    @NotBlank(message = "A nova senha é obrigatória")
    @Size(max = 255, message = "A senha deve ter no mínimo 6 caracteres")
    private String novaSenha;

    @NotBlank(message = "A confirmação de senha é obrigatória")
    private String confirmarSenha;
}
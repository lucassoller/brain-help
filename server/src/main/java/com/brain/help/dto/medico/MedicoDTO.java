package com.brain.help.dto.medico;

import com.brain.help.dto.endereco.EnderecoConsultorioDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MedicoDTO {

    @NotBlank(message = "O nome é obrigatório")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
    private String nome;

    @NotBlank(message = "O sobrenome é obrigatório")
    @Size(max = 100, message = "O sobrenome deve ter no máximo 100 caracteres")
    private String sobrenome;

    @NotBlank(message = "O email é obrigatório")
    @Email(message = "Formato de email inválido")
    @Size(max = 150, message = "O email deve ter no máximo 150 caracteres")
    private String email;

    @NotBlank(message = "O telefone é obrigatório")
    @Size(max = 20, message = "O telefone deve ter no máximo 20 caracteres")
    private String telefone;

    @NotBlank(message = "A especialização é obrigatória")
    @Size(max = 100, message = "A especialização deve ter no máximo 100 caracteres")
    private String especializacao;

    @Size(max = 255, message = "A senha deve ter no máximo 255 caracteres")
    private String senha;

    @Size(max = 255, message = "A confirmação de senha deve ter no máximo 255 caracteres")
    private String confirmarSenha;

    @NotNull(message = "O endereço é obrigatório")
    @Valid
    private EnderecoConsultorioDTO enderecoConsultorio;

    private String foto;
}
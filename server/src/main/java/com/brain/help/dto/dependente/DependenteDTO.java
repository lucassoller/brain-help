package com.brain.help.dto.dependente;

import com.brain.help.dto.endereco.EnderecoDTO;
import com.brain.help.enums.EstagioAlzheimer;
import com.brain.help.enums.Sexo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.time.LocalDate;

@Data
public class DependenteDTO {

    @NotBlank(message = "O nome é obrigatório")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
    private String nome;

    @NotBlank(message = "O sobrenome é obrigatório")
    @Size(max = 100, message = "O sobrenome deve ter no máximo 100 caracteres")
    private String sobrenome;

    @NotBlank(message = "O email é obrigatório")
    @Size(max = 100, message = "O email deve ter no máximo 150 caracteres")
    private String email;

    @NotBlank(message = "O telefone é obrigatório")
    @Size(max = 100, message = "O telefone deve ter no máximo 20 caracteres")
    private String telefone;

    @NotBlank(message = "A senha é obrigatória")
    @Size(max = 255, message = "A senha deve ter no máximo 255 caracteres")
    private String senha;

    @NotBlank(message = "A confirmação de senha é obrigatória")
    @Size(max = 255, message = "A confirmação de senha deve ter no máximo 255 caracteres")
    private String confirmarSenha;

    @NotNull(message = "A data de nascimento é obrigatória")
    private LocalDate dataNascimento;

    private LocalDate dataDiagnostico;

    @NotNull(message = "O sexo é obrigatório")
    private Sexo sexo;

    @NotNull(message = "O estágio do alzheimer é obrigatório")
    private EstagioAlzheimer estagioAlzheimer;

    private String foto;

    @NotNull(message = "O endereço é obrigatório")
    @Valid
    private EnderecoDTO endereco;

    @Size(max = 255, message = "A chave de segurança deve ter no máximo 255 caracteres")
    private String chaveSeguranca;

}

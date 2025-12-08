package com.helpsync.usecases.manter_doador;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DoadorRequest(
        @NotBlank(message = "Nome é obrigatório")
        @Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres")
        String nome,

        @NotBlank(message = "CPF é obrigatório")
        @Size(min = 11, max = 11, message = "CPF deve ter 11 caracteres")
        String cpf,

        @NotBlank(message = "Email é obrigatório")
        @Email(message = "Email deve ter um formato válido")
        String email,

        @NotBlank(message = "Senha é obrigatória")
        @Size(min = 6, message = "Senha deve ter pelo menos 6 caracteres")
        String senha
) {}
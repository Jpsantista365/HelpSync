package com.helpsync.usecases.manter_instituicao;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record InstituicaoRequest(
        @NotBlank(message = "Nome é obrigatório")
        String nome,

        @NotBlank(message = "CNPJ é obrigatório")
        @Size(min = 14, max = 14, message = "CNPJ deve ter 14 caracteres")
        String cnpj,

        @NotBlank(message = "Endereço é obrigatório")
        String endereco,

        @NotNull(message = "ID do Fundo Municipal é obrigatório")
        UUID fundoMunicipalId
) {}
package com.helpsync.usecases.manter_fundoMunicipal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record FundoMunicipalRequest(
        @NotBlank(message = "Nome é obrigatório")
        String nome,

        String descricao,

        @NotNull(message = "ID do Administrador é obrigatório")
        UUID administradorId
) {}

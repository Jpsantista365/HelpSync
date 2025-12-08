package com.helpsync.usecases.manter_relatorio;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record RelatorioRequest(
        @NotBlank(message = "Tipo é obrigatório")
        String tipo,

        @NotBlank(message = "Conteúdo é obrigatório")
        String conteudo,

        @NotNull(message = "ID do Administrador (autor) é obrigatório")
        UUID administradorId,

        @NotNull(message = "ID da Instituição (assunto) é obrigatório")
        UUID instituicaoId
) {}

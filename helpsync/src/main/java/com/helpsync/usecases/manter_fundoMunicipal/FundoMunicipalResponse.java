package com.helpsync.usecases.manter_fundoMunicipal;

import com.helpsync.entity.FundoMunicipal;

import java.time.LocalDateTime;
import java.util.UUID;

public record FundoMunicipalResponse(
        UUID id,
        String nome,
        String descricao,
        AdminInfo administrador,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public record AdminInfo(
            UUID id,
            String nome,
            String email
    ) {}

    public static FundoMunicipalResponse fromEntity(FundoMunicipal fundo) {
        AdminInfo adminInfo = new AdminInfo(
                fundo.getAdministrador().getId(),
                fundo.getAdministrador().getNome(),
                fundo.getAdministrador().getEmail()
        );

        return new FundoMunicipalResponse(
                fundo.getId(),
                fundo.getNome(),
                fundo.getDescricao(),
                adminInfo,
                fundo.getCreatedAt(),
                fundo.getUpdatedAt()
        );
    }
}
package com.helpsync.usecases.manter_campanha;

import com.helpsync.entity.Campanha;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record CampanhaResponse(
        UUID id,
        String titulo,
        String descricao,
        BigDecimal metaFinanceira,
        InstituicaoInfo instituicao,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public record InstituicaoInfo(
            UUID id,
            String nome
    ) {}

    public static CampanhaResponse fromEntity(Campanha campanha) {
        InstituicaoInfo instituicaoInfo = new InstituicaoInfo(
                campanha.getInstituicao().getId(),
                campanha.getInstituicao().getNome()
        );

        return new CampanhaResponse(
                campanha.getId(),
                campanha.getTitulo(),
                campanha.getDescricao(),
                campanha.getMetaFinanceira(),
                instituicaoInfo,
                campanha.getCreatedAt(),
                campanha.getUpdatedAt()
        );
    }
}

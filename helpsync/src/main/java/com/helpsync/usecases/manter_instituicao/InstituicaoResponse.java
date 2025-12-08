package com.helpsync.usecases.manter_instituicao;

import com.helpsync.entity.Instituicao;

import java.time.LocalDateTime;
import java.util.UUID;

public record InstituicaoResponse(
        UUID id,
        String nome,
        String cnpj,
        String endereco,
        FundoInfo fundoMunicipal,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

    public record FundoInfo(
            UUID id,
            String nome
    ) {}

    public static InstituicaoResponse fromEntity(Instituicao instituicao) {
        FundoInfo fundoInfo = new FundoInfo(
                instituicao.getFundoMunicipal().getId(),
                instituicao.getFundoMunicipal().getNome()
        );

        return new InstituicaoResponse(
                instituicao.getId(),
                instituicao.getNome(),
                instituicao.getCnpj(),
                instituicao.getEndereco(),
                fundoInfo,
                instituicao.getCreatedAt(),
                instituicao.getUpdatedAt()
        );
    }
}
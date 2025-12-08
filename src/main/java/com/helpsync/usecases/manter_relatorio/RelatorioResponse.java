package com.helpsync.usecases.manter_relatorio;

import com.helpsync.entity.Relatorio;
import java.time.LocalDateTime;
import java.util.UUID;

public record RelatorioResponse(
        UUID id,
        String tipo,
        String conteudo,
        AdminInfo administrador,
        InstituicaoInfo instituicao,
        LocalDateTime createdAt
) {
    public record AdminInfo(UUID id, String nome) {}
    public record InstituicaoInfo(UUID id, String nome) {}

    public static RelatorioResponse fromEntity(Relatorio relatorio) {
        AdminInfo adminInfo = new AdminInfo(
                relatorio.getAdministrador().getId(),
                relatorio.getAdministrador().getNome()
        );

        InstituicaoInfo instituicaoInfo = new InstituicaoInfo(
                relatorio.getInstituicao().getId(),
                relatorio.getInstituicao().getNome()
        );

        return new RelatorioResponse(
                relatorio.getId(),
                relatorio.getTipo(),
                relatorio.getConteudo(),
                adminInfo,
                instituicaoInfo,
                relatorio.getCreatedAt()
        );
    }
}

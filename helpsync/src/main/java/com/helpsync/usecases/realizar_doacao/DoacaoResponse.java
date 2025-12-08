package com.helpsync.usecases.realizar_doacao;

import com.helpsync.entity.Doacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record DoacaoResponse(
        UUID id,
        BigDecimal valor,
        String metodoPagamento,
        Boolean anonima,
        DoadorInfo doador,
        CampanhaInfo campanha,
        LocalDateTime createdAt
) {
    public record DoadorInfo(
            UUID id,
            String nome,
            String cpf
    ) {}

    public record CampanhaInfo(
            UUID id,
            String titulo
    ) {}

    public static DoacaoResponse fromEntity(Doacao doacao) {
        DoadorInfo doadorInfo;
        if (doacao.getAnonima()) {
            doadorInfo = new DoadorInfo(null, "Anônimo", null);
        } else {
            doadorInfo = new DoadorInfo(
                    doacao.getDoador().getId(),
                    doacao.getDoador().getNome(),
                    doacao.getDoador().getCpf() 
            );
        }

        CampanhaInfo campanhaInfo = new CampanhaInfo(
                doacao.getCampanha().getId(),
                doacao.getCampanha().getTitulo()
        );

        return new DoacaoResponse(
                doacao.getId(),
                doacao.getValor(),
                doacao.getMetodoPagamento(),
                doacao.getAnonima(),
                doadorInfo,
                campanhaInfo,
                doacao.getCreatedAt()
        );
    }
}

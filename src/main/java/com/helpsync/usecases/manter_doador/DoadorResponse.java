package com.helpsync.usecases.manter_doador;

import com.helpsync.entity.Doador;
import java.time.LocalDateTime;
import java.util.UUID;

public record DoadorResponse(
        UUID id,
        String nome,
        String cpf,
        String email,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    
    public static DoadorResponse fromEntity(Doador doador) {
        return new DoadorResponse(
                doador.getId(),
                doador.getNome(),
                doador.getCpf(),
                doador.getEmail(),
                doador.getCreatedAt(),
                doador.getUpdatedAt()
        );
    }
}
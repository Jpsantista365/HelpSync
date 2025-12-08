package com.helpsync.usecases.manter_administrador;

import com.helpsync.entity.Administrador;

import java.time.LocalDateTime;
import java.util.UUID;

public record AdministradorResponse(
        UUID id,
        String nome,
        String email,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static AdministradorResponse fromEntity(Administrador administrador) {
        return new AdministradorResponse(
                administrador.getId(),
                administrador.getNome(),
                administrador.getEmail(),
                administrador.getCreatedAt(),
                administrador.getUpdatedAt()
        );
    }
}
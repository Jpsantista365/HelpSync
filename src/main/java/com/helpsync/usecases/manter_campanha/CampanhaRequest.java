package com.helpsync.usecases.manter_campanha;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record CampanhaRequest(
        @NotBlank(message = "Título é obrigatório")
        String titulo,

        String descricao,

        @NotNull(message = "Meta financeira é obrigatória")
        @DecimalMin(value = "0.0", inclusive = false, message = "Meta financeira deve ser maior que zero")
        BigDecimal metaFinanceira,

        @NotNull(message = "Data de início é obrigatória")
        LocalDateTime dataInicio,

        @NotNull(message = "Data de fim é obrigatória")
        LocalDateTime dataFim,

        @NotNull(message = "ID da Instituição é obrigatório")
        UUID instituicaoId
) {}
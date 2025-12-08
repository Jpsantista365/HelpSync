package com.helpsync.usecases.realizar_doacao;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record DoacaoRequest(
        @NotNull(message = "Valor é obrigatório")
        @DecimalMin(value = "0.01", message = "Valor da doação deve ser de pelo menos 0.01")
        BigDecimal valor,

        @NotBlank(message = "Método de pagamento é obrigatório")
        String metodoPagamento,

        @NotNull(message = "Status 'anônima' é obrigatório")
        Boolean anonima,

        @NotNull(message = "ID do Doador é obrigatório")
        UUID doadorId,

        @NotNull(message = "ID da Campanha é obrigatório")
        UUID campanhaId
) {}

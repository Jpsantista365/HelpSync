package com.helpsync.usecases.manter_relatorio;

import java.math.BigDecimal;

public record RelatorioEstatisticasDTO(
        String nomeInstituicao,
        long totalCampanhas,
        long totalDoacoes,
        BigDecimal valorTotalArrecadado
) {}

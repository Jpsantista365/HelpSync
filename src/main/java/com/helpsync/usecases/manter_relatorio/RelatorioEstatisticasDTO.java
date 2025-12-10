package com.helpsync.usecases.manter_relatorio;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RelatorioEstatisticasDTO {
    private String nomeInstituicao;
    private long totalCampanhas;
    private long totalDoacoes;
    private BigDecimal valorTotalArrecadado;
}

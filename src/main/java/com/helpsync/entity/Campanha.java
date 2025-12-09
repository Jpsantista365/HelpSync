package com.helpsync.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "campanha")
public class Campanha extends BaseEntity {

    @Column(name = "valor_arrecadado", precision = 10, scale = 2)
    private BigDecimal valorArrecadado = BigDecimal.ZERO;
    
    @Column(nullable = false)
    private String titulo;
    
    @Column(columnDefinition = "TEXT")
    private String descricao;
    
    @Column(name = "meta_financeira", precision = 10, scale = 2)
    private BigDecimal metaFinanceira;

    @Column(name = "data_inicio")
    private LocalDateTime dataInicio;

    @Column(name = "data_fim")
    private LocalDateTime dataFim;

    @Column(nullable = false)
    private Boolean ativa = true;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instituicao_id")
    private Instituicao instituicao;
    
    @OneToMany(mappedBy = "campanha", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Doacao> doacoes;
    
    public Campanha(String titulo, String descricao, BigDecimal metaFinanceira, LocalDateTime dataInicio, LocalDateTime dataFim) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.metaFinanceira = metaFinanceira;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.ativa = true;
    }
}
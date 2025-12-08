package com.helpsync.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "doacao")
public class Doacao extends BaseEntity {
    
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valor;
    
    @Column(name = "metodo_pagamento", nullable = false)
    private String metodoPagamento;
    
    @Column(nullable = false)
    private Boolean anonima = false;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doador_id")
    private Doador doador;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "campanha_id")
    private Campanha campanha;
    
    public Doacao(BigDecimal valor, String metodoPagamento, Boolean anonima) {
        this.valor = valor;
        this.metodoPagamento = metodoPagamento;
        this.anonima = anonima;
    }
}
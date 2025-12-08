package com.helpsync.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "relatorio")
public class Relatorio extends BaseEntity {
    
    @Column(nullable = false)
    private String tipo;
    
    @Column(columnDefinition = "TEXT")
    private String conteudo;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "administrador_id")
    private Administrador administrador;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instituicao_id")
    private Instituicao instituicao;
    
    public Relatorio(String tipo, String conteudo) {
        this.tipo = tipo;
        this.conteudo = conteudo;
    }
}
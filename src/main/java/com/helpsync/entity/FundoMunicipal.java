package com.helpsync.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "fundo_municipal")
public class FundoMunicipal extends BaseEntity {
    
    @Column(nullable = false)
    private String nome;
    
    @Column(columnDefinition = "TEXT")
    private String descricao;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "administrador_id", nullable = false)
    private Administrador administrador;
    
    @OneToMany(mappedBy = "fundoMunicipal", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Instituicao> instituicoes;
    
    public FundoMunicipal(String nome, String descricao, Administrador administrador) {
        this.nome = nome;
        this.descricao = descricao;
        this.administrador = administrador;
    }
}
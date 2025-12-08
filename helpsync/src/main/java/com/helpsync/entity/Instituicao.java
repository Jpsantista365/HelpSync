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
@Table(name = "instituicao")
public class Instituicao extends BaseEntity {
    
    @Column(nullable = false)
    private String nome;
    
    @Column(nullable = false, unique = true)
    private String cnpj;
    
    @Column(nullable = false)
    private String endereco;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fundo_municipal_id")
    private FundoMunicipal fundoMunicipal;
    
    @OneToMany(mappedBy = "instituicao", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Campanha> campanhas;
    
    @OneToMany(mappedBy = "instituicao", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Relatorio> relatorios;
    
    public Instituicao(String nome, String cnpj, String endereco) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.endereco = endereco;
    }
}
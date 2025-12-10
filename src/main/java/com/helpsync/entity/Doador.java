package com.helpsync.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "doador")
public class Doador extends BaseEntity {
    
    @Column(nullable = false)
    private String nome;
    
    @Column(unique = true, nullable = false)
    private String cpf;
    
    @Column(nullable = false, unique = true)
    private String email;
    
    @Column(nullable = false)
    private String senha;

    @ToString.Exclude
    @OneToMany(mappedBy = "doador", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Doacao> doacoes;
}
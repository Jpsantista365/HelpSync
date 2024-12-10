/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package br.com.helpsync.entities;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author 06248596140
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Doacao {
    private String descricao;
    private String categoria;
    private String urgencia;
    private int quantidadeNecessaria;
    private LocalDateTime dataDeAtualizacao; 
}

package com.helpsync.usecases.realizar_doacao;

import com.helpsync.entity.Doacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Repository
public interface DoacaoRepository extends JpaRepository<Doacao, UUID> {

    List<Doacao> findByDoadorId(UUID doadorId);
    List<Doacao> findByCampanhaId(UUID campanhaId);

    @Query("SELECT SUM(d.valor) FROM Doacao d WHERE d.campanha.instituicao.id = :instituicaoId")
    BigDecimal somarDoacoesPorInstituicao(@Param("instituicaoId") UUID instituicaoId);

    @Query("SELECT COUNT(d) FROM Doacao d WHERE d.campanha.instituicao.id = :instituicaoId")
    long contarDoacoesPorInstituicao(@Param("instituicaoId") UUID instituicaoId);
}
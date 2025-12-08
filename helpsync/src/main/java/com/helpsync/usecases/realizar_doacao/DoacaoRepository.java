package com.helpsync.usecases.realizar_doacao;

import com.helpsync.entity.Doacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DoacaoRepository extends JpaRepository<Doacao, UUID> {

    List<Doacao> findByDoadorId(UUID doadorId);

    List<Doacao> findByCampanhaId(UUID campanhaId);
}

package com.helpsync.usecases.manter_instituicao;

import com.helpsync.entity.Instituicao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface InstituicaoRepository extends JpaRepository<Instituicao, UUID> {

    Optional<Instituicao> findByCnpj(String cnpj);

    boolean existsByCnpj(String cnpj);

    List<Instituicao> findByFundoMunicipalId(UUID fundoMunicipalId);
}
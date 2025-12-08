package com.helpsync.usecases.manter_relatorio;

import com.helpsync.entity.Relatorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RelatorioRepository extends JpaRepository<Relatorio, UUID> {

    List<Relatorio> findByAdministradorId(UUID administradorId);

    List<Relatorio> findByInstituicaoId(UUID instituicaoId);
}

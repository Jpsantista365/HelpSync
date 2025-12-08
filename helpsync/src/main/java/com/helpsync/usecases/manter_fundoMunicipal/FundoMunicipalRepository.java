package com.helpsync.usecases.manter_fundoMunicipal;

import com.helpsync.entity.FundoMunicipal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FundoMunicipalRepository extends JpaRepository<FundoMunicipal, UUID> {

    List<FundoMunicipal> findByAdministradorId(UUID administradorId);
}

package com.helpsync.usecases.manter_administrador;

import com.helpsync.entity.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, UUID> {
    
    Optional<Administrador> findByEmail(String email);
    
    boolean existsByEmail(String email);
}
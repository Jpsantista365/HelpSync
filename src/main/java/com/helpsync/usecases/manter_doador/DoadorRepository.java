package com.helpsync.usecases.manter_doador;

import com.helpsync.entity.Doador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DoadorRepository extends JpaRepository<Doador, UUID> {

    Optional<Doador> findByEmail(String email);

    boolean existsByEmail(String email);

    Optional<Doador> findByCpf(String cpf);

    boolean existsByCpf(String cpf);
}

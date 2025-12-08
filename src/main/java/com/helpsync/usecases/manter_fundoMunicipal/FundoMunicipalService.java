package com.helpsync.usecases.manter_fundoMunicipal;

import com.helpsync.entity.Administrador;
import com.helpsync.entity.FundoMunicipal;
import com.helpsync.usecases.manter_administrador.AdministradorRepository; // Importando
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class FundoMunicipalService {

    private final FundoMunicipalRepository fundoMunicipalRepository;
    private final AdministradorRepository administradorRepository;

    public FundoMunicipalResponse criar(FundoMunicipalRequest request) {
        Administrador admin = administradorRepository.findById(request.administradorId())
                .orElseThrow(() -> new RuntimeException("Administrador não encontrado com ID: " + request.administradorId()));

        FundoMunicipal fundo = new FundoMunicipal();
        fundo.setNome(request.nome());
        fundo.setDescricao(request.descricao());
        fundo.setAdministrador(admin); 

        FundoMunicipal salvo = fundoMunicipalRepository.save(fundo);
        return FundoMunicipalResponse.fromEntity(salvo);
    }

    @Transactional(readOnly = true)
    public FundoMunicipalResponse buscarPorId(UUID id) {
        FundoMunicipal fundo = fundoMunicipalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fundo Municipal não encontrado com ID: " + id));
        return FundoMunicipalResponse.fromEntity(fundo);
    }

    @Transactional(readOnly = true)
    public List<FundoMunicipalResponse> listarTodos() {
        return fundoMunicipalRepository.findAll()
                .stream()
                .map(FundoMunicipalResponse::fromEntity)
                .collect(Collectors.toList());
    }

    public FundoMunicipalResponse atualizar(UUID id, FundoMunicipalRequest request) {
        FundoMunicipal fundo = fundoMunicipalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fundo Municipal não encontrado com ID: " + id));

        Administrador admin = administradorRepository.findById(request.administradorId())
                .orElseThrow(() -> new RuntimeException("Administrador não encontrado com ID: " + request.administradorId()));

        fundo.setNome(request.nome());
        fundo.setDescricao(request.descricao());
        fundo.setAdministrador(admin);

        FundoMunicipal atualizado = fundoMunicipalRepository.save(fundo);
        return FundoMunicipalResponse.fromEntity(atualizado);
    }

    public void deletar(UUID id) {
        if (!fundoMunicipalRepository.existsById(id)) {
            throw new RuntimeException("Fundo Municipal não encontrado com ID: " + id);
        }
        fundoMunicipalRepository.deleteById(id);
    }
}

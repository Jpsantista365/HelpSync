package com.helpsync.usecases.manter_instituicao;

import com.helpsync.entity.FundoMunicipal;
import com.helpsync.entity.Instituicao;
import com.helpsync.usecases.manter_fundoMunicipal.FundoMunicipalRepository; // Importando
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class InstituicaoService {

    private final InstituicaoRepository instituicaoRepository;
    private final FundoMunicipalRepository fundoMunicipalRepository; 

    public InstituicaoResponse criar(InstituicaoRequest request) {
        if (instituicaoRepository.existsByCnpj(request.cnpj())) {
            throw new RuntimeException("Erro: CNPJ já está em uso");
        }

        FundoMunicipal fundo = fundoMunicipalRepository.findById(request.fundoMunicipalId())
                .orElseThrow(() -> new RuntimeException("Fundo Municipal não encontrado com ID: " + request.fundoMunicipalId()));

        Instituicao instituicao = new Instituicao();
        instituicao.setNome(request.nome());
        instituicao.setCnpj(request.cnpj());
        instituicao.setEndereco(request.endereco());
        instituicao.setFundoMunicipal(fundo); 

        Instituicao salva = instituicaoRepository.save(instituicao);
        return InstituicaoResponse.fromEntity(salva);
    }

    @Transactional(readOnly = true)
    public InstituicaoResponse buscarPorId(UUID id) {
        Instituicao instituicao = instituicaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Instituição não encontrada com ID: " + id));
        return InstituicaoResponse.fromEntity(instituicao);
    }

    @Transactional(readOnly = true)
    public List<InstituicaoResponse> listarTodos() {
        return instituicaoRepository.findAll()
                .stream()
                .map(InstituicaoResponse::fromEntity)
                .collect(Collectors.toList());
    }

    public InstituicaoResponse atualizar(UUID id, InstituicaoRequest request) {
        Instituicao instituicao = instituicaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Instituição não encontrada com ID: " + id));

        if (!instituicao.getCnpj().equals(request.cnpj()) &&
                instituicaoRepository.existsByCnpj(request.cnpj())) {
            throw new RuntimeException("Erro: CNPJ já está em uso");
        }

        FundoMunicipal fundo = fundoMunicipalRepository.findById(request.fundoMunicipalId())
                .orElseThrow(() -> new RuntimeException("Fundo Municipal não encontrado com ID: " + request.fundoMunicipalId()));

        instituicao.setNome(request.nome());
        instituicao.setCnpj(request.cnpj());
        instituicao.setEndereco(request.endereco());
        instituicao.setFundoMunicipal(fundo);

        Instituicao atualizada = instituicaoRepository.save(instituicao);
        return InstituicaoResponse.fromEntity(atualizada);
    }

    public void deletar(UUID id) {
        if (!instituicaoRepository.existsById(id)) {
            throw new RuntimeException("Instituição não encontrada com ID: " + id);
        }
        instituicaoRepository.deleteById(id);
    }
}

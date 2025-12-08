package com.helpsync.usecases.manter_campanha;

import com.helpsync.entity.Campanha;
import com.helpsync.entity.Instituicao;
import com.helpsync.usecases.manter_instituicao.InstituicaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CampanhaService {

    private final CampanhaRepository campanhaRepository;
    private final InstituicaoRepository instituicaoRepository;

    public CampanhaResponse criar(CampanhaRequest request) {
        Instituicao instituicao = instituicaoRepository.findById(request.instituicaoId())
                .orElseThrow(() -> new RuntimeException("Instituição não encontrada com ID: " + request.instituicaoId()));
        Campanha campanha = new Campanha();
        campanha.setTitulo(request.titulo());
        campanha.setDescricao(request.descricao());
        campanha.setMetaFinanceira(request.metaFinanceira());
        campanha.setInstituicao(instituicao); 

        Campanha salva = campanhaRepository.save(campanha);
        return CampanhaResponse.fromEntity(salva);
    }

    @Transactional(readOnly = true)
    public CampanhaResponse buscarPorId(UUID id) {
        Campanha campanha = campanhaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Campanha não encontrada com ID: " + id));
        return CampanhaResponse.fromEntity(campanha);
    }

    @Transactional(readOnly = true)
    public List<CampanhaResponse> listarTodos() {
        return campanhaRepository.findAll()
                .stream()
                .map(CampanhaResponse::fromEntity)
                .collect(Collectors.toList());
    }

    public CampanhaResponse atualizar(UUID id, CampanhaRequest request) {
        Campanha campanha = campanhaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Campanha não encontrada com ID: " + id));
        Instituicao instituicao = instituicaoRepository.findById(request.instituicaoId())
                .orElseThrow(() -> new RuntimeException("Instituição não encontrada com ID: " + request.instituicaoId()));
        campanha.setTitulo(request.titulo());
        campanha.setDescricao(request.descricao());
        campanha.setMetaFinanceira(request.metaFinanceira());
        campanha.setInstituicao(instituicao);

        Campanha atualizada = campanhaRepository.save(campanha);
        return CampanhaResponse.fromEntity(atualizada);
    }

    public void deletar(UUID id) {
        if (!campanhaRepository.existsById(id)) {
            throw new RuntimeException("Campanha não encontrada com ID: " + id);
        }
        campanhaRepository.deleteById(id);
    }
}
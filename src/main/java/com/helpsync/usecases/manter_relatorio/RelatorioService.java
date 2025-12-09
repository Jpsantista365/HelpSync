package com.helpsync.usecases.manter_relatorio;

import com.helpsync.entity.Administrador;
import com.helpsync.entity.Instituicao;
import com.helpsync.entity.Relatorio;
import com.helpsync.usecases.manter_administrador.AdministradorRepository;
import com.helpsync.usecases.manter_campanha.CampanhaRepository;
import com.helpsync.usecases.manter_instituicao.InstituicaoRepository;
import com.helpsync.usecases.realizar_doacao.DoacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class RelatorioService {

    private final RelatorioRepository relatorioRepository;
    private final AdministradorRepository administradorRepository;
    private final InstituicaoRepository instituicaoRepository; 

    private final CampanhaRepository campanhaRepository;
    private final DoacaoRepository doacaoRepository;

    public RelatorioResponse criar(RelatorioRequest request) {
        Administrador admin = administradorRepository.findById(request.administradorId())
                .orElseThrow(() -> new RuntimeException("Administrador não encontrado com ID: " + request.administradorId()));
        Instituicao instituicao = instituicaoRepository.findById(request.instituicaoId())
                .orElseThrow(() -> new RuntimeException("Instituição não encontrada com ID: " + request.instituicaoId()));

        Relatorio relatorio = new Relatorio();
        relatorio.setTipo(request.tipo());
        relatorio.setConteudo(request.conteudo());
        relatorio.setAdministrador(admin);
        relatorio.setInstituicao(instituicao);

        Relatorio salvo = relatorioRepository.save(relatorio);
        return RelatorioResponse.fromEntity(salvo);
    }

    @Transactional(readOnly = true)
    public RelatorioResponse buscarPorId(UUID id) {
        Relatorio relatorio = relatorioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Relatório não encontrado com ID: " + id));
        return RelatorioResponse.fromEntity(relatorio);
    }

    @Transactional(readOnly = true)
    public List<RelatorioResponse> listarTodos() {
        return relatorioRepository.findAll()
                .stream()
                .map(RelatorioResponse::fromEntity)
                .collect(Collectors.toList());
    }

    public RelatorioResponse atualizar(UUID id, RelatorioRequest request) {
        Relatorio relatorio = relatorioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Relatório não encontrado com ID: " + id));

        Administrador admin = administradorRepository.findById(request.administradorId())
                .orElseThrow(() -> new RuntimeException("Administrador não encontrado com ID: " + request.administradorId()));

        Instituicao instituicao = instituicaoRepository.findById(request.instituicaoId())
                .orElseThrow(() -> new RuntimeException("Instituição não encontrada com ID: " + request.instituicaoId()));

        relatorio.setTipo(request.tipo());
        relatorio.setConteudo(request.conteudo());
        relatorio.setAdministrador(admin);
        relatorio.setInstituicao(instituicao);

        Relatorio atualizado = relatorioRepository.save(relatorio);
        return RelatorioResponse.fromEntity(atualizado);
    }

    public void deletar(UUID id) {
        if (!relatorioRepository.existsById(id)) {
            throw new RuntimeException("Relatório não encontrado com ID: " + id);
        }
        relatorioRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public RelatorioEstatisticasDTO gerarEstatisticas(UUID instituicaoId) {
        Instituicao instituicao = instituicaoRepository.findById(instituicaoId)
                .orElseThrow(() -> new RuntimeException("Instituição não encontrada com ID: " + instituicaoId));
        long totalCampanhas = campanhaRepository.countByInstituicaoId(instituicaoId);
        long totalDoacoes = doacaoRepository.contarDoacoesPorInstituicao(instituicaoId);
        BigDecimal valorTotal = doacaoRepository.somarDoacoesPorInstituicao(instituicaoId);

        if (valorTotal == null) {
            valorTotal = BigDecimal.ZERO;
        }

        return new RelatorioEstatisticasDTO(
                instituicao.getNome(),
                totalCampanhas,
                totalDoacoes,
                valorTotal
        );
    }
}
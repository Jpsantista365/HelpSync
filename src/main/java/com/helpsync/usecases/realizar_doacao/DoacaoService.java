package com.helpsync.usecases.realizar_doacao;

import com.helpsync.entity.Campanha;
import com.helpsync.entity.Doacao;
import com.helpsync.entity.Doador;
import com.helpsync.usecases.manter_campanha.CampanhaRepository;
import com.helpsync.usecases.manter_doador.DoadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class DoacaoService {

    private final DoacaoRepository doacaoRepository;
    private final DoadorRepository doadorRepository; 
    private final CampanhaRepository campanhaRepository; 

    public DoacaoResponse criar(DoacaoRequest request) {
        Doador doador = doadorRepository.findById(request.doadorId())
                .orElseThrow(() -> new RuntimeException("Doador não encontrado com ID: " + request.doadorId()));
        
        Campanha campanha = campanhaRepository.findById(request.campanhaId())
                .orElseThrow(() -> new RuntimeException("Campanha não encontrada com ID: " + request.campanhaId()));
        if (!campanha.getAtiva()) {
            throw new RuntimeException("Erro: Esta campanha está encerrada ou inativa.");
        }
        LocalDateTime agora = LocalDateTime.now();
        if (agora.isBefore(campanha.getDataInicio())) {
            throw new RuntimeException("Erro: Esta campanha ainda não começou.");
        }
        if (agora.isAfter(campanha.getDataFim())) {
            throw new RuntimeException("Erro: O prazo desta campanha já acabou.");
        }

        Doacao doacao = new Doacao();
        doacao.setValor(request.valor());
        doacao.setMetodoPagamento(request.metodoPagamento());
        doacao.setAnonima(request.anonima());
        doacao.setDoador(doador);
        doacao.setCampanha(campanha);

        BigDecimal atual = campanha.getValorArrecadado() != null ? campanha.getValorArrecadado() : BigDecimal.ZERO;
        campanha.setValorArrecadado(atual.add(request.valor()));
        
        campanhaRepository.save(campanha); 

        Doacao salva = doacaoRepository.save(doacao);
        return DoacaoResponse.fromEntity(salva);
    }

    @Transactional(readOnly = true)
    public DoacaoResponse buscarPorId(UUID id) {
        Doacao doacao = doacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doação não encontrada com ID: " + id));
        return DoacaoResponse.fromEntity(doacao);
    }

    @Transactional(readOnly = true)
    public List<DoacaoResponse> listarTodos() {
        return doacaoRepository.findAll()
                .stream()
                .map(DoacaoResponse::fromEntity)
                .collect(Collectors.toList());
    }
}

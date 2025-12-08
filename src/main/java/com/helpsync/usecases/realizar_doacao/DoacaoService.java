package com.helpsync.usecases.realizar_doacao;

import com.helpsync.entity.Campanha;
import com.helpsync.entity.Doacao;
import com.helpsync.entity.Doador;
import com.helpsync.usecases.manter_campanha.CampanhaRepository;
import com.helpsync.usecases.manter_doador.DoadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        Doacao doacao = new Doacao();
        doacao.setValor(request.valor());
        doacao.setMetodoPagamento(request.metodoPagamento());
        doacao.setAnonima(request.anonima());
        doacao.setDoador(doador);
        doacao.setCampanha(campanha);

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

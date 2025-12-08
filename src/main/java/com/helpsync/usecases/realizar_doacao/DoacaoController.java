package com.helpsync.usecases.realizar_doacao;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.helpsync.entity.Doacao;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/doacoes")
@RequiredArgsConstructor
public class DoacaoController {

    private final DoacaoService doacaoService;
    private final DoacaoRepository doacaoRepository;

    @PostMapping
    public ResponseEntity<?> criar(@Valid @RequestBody DoacaoRequest request) {
        try {
            DoacaoResponse response = doacaoService.criar(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoacaoResponse> buscarPorId(@PathVariable UUID id) {
        try {
            DoacaoResponse response = doacaoService.buscarPorId(id);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<DoacaoResponse>> listarTodos() {
        List<DoacaoResponse> doacoes = doacaoService.listarTodos();
        return ResponseEntity.ok(doacoes);
    }
    
    @GetMapping("/por-doador/{doadorId}")
    public ResponseEntity<List<Doacao>> listarPorDoador(@PathVariable UUID doadorId) {
        List<Doacao> doacoes = doacaoRepository.findByDoadorId(doadorId);
        if (doacoes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(doacoes);
    }

    @GetMapping("/por-campanha/{campanhaId}")
    public ResponseEntity<List<Doacao>> listarPorCampanha(@PathVariable UUID campanhaId) {
        List<Doacao> doacoes = doacaoRepository.findByCampanhaId(campanhaId);
        if (doacoes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(doacoes);
    }
}
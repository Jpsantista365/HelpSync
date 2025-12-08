package com.helpsync.usecases.manter_campanha;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/campanhas")
@RequiredArgsConstructor
public class CampanhaController {

    private final CampanhaService campanhaService;

    @PostMapping
    public ResponseEntity<?> criar(@Valid @RequestBody CampanhaRequest request) {
        try {
            CampanhaResponse response = campanhaService.criar(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CampanhaResponse> buscarPorId(@PathVariable UUID id) {
        try {
            CampanhaResponse response = campanhaService.buscarPorId(id);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<CampanhaResponse>> listarTodos() {
        List<CampanhaResponse> campanhas = campanhaService.listarTodos();
        return ResponseEntity.ok(campanhas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(
            @PathVariable UUID id,
            @Valid @RequestBody CampanhaRequest request) {
        try {
            CampanhaResponse response = campanhaService.atualizar(id, request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        try {
            campanhaService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

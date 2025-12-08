package com.helpsync.usecases.manter_relatorio;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/relatorios")
@RequiredArgsConstructor
public class RelatorioController {

    private final RelatorioService relatorioService;

    @PostMapping
    public ResponseEntity<?> criar(@Valid @RequestBody RelatorioRequest request) {
        try {
            RelatorioResponse response = relatorioService.criar(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<RelatorioResponse> buscarPorId(@PathVariable UUID id) {
        try {
            RelatorioResponse response = relatorioService.buscarPorId(id);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<RelatorioResponse>> listarTodos() {
        List<RelatorioResponse> relatorios = relatorioService.listarTodos();
        return ResponseEntity.ok(relatorios);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(
            @PathVariable UUID id,
            @Valid @RequestBody RelatorioRequest request) {
        try {
            RelatorioResponse response = relatorioService.atualizar(id, request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        try {
            relatorioService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
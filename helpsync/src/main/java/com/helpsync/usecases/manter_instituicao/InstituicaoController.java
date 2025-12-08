package com.helpsync.usecases.manter_instituicao;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/instituicoes")
@RequiredArgsConstructor
public class InstituicaoController {

    private final InstituicaoService instituicaoService;

    @PostMapping
    public ResponseEntity<?> criar(@Valid @RequestBody InstituicaoRequest request) {
        try {
            InstituicaoResponse response = instituicaoService.criar(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<InstituicaoResponse> buscarPorId(@PathVariable UUID id) {
        try {
            InstituicaoResponse response = instituicaoService.buscarPorId(id);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<InstituicaoResponse>> listarTodos() {
        List<InstituicaoResponse> instituicoes = instituicaoService.listarTodos();
        return ResponseEntity.ok(instituicoes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(
            @PathVariable UUID id,
            @Valid @RequestBody InstituicaoRequest request) {
        try {
            InstituicaoResponse response = instituicaoService.atualizar(id, request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        try {
            instituicaoService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

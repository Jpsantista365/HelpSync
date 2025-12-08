package com.helpsync.usecases.manter_doador;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/doadores")
@RequiredArgsConstructor
public class DoadorController {
    
    private final DoadorService doadorService;

    @PostMapping
    public ResponseEntity<?> criar(@Valid @RequestBody DoadorRequest request) {
        try {
            DoadorResponse response = doadorService.criar(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoadorResponse> buscarPorId(@PathVariable UUID id) {
        try {
            DoadorResponse response = doadorService.buscarPorId(id);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<DoadorResponse>> listarTodos() {
        List<DoadorResponse> doadores = doadorService.listarTodos();
        return ResponseEntity.ok(doadores);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(
            @PathVariable UUID id,
            @Valid @RequestBody DoadorRequest request) {
        try {
            DoadorResponse response = doadorService.atualizar(id, request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        try {
            doadorService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

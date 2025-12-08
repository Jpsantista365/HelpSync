package com.helpsync.usecases.manter_fundoMunicipal;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/fundos-municipais")
@RequiredArgsConstructor
public class FundoMunicipalController {

    private final FundoMunicipalService fundoMunicipalService;

    @PostMapping
    public ResponseEntity<?> criar(@Valid @RequestBody FundoMunicipalRequest request) {
        try {
            FundoMunicipalResponse response = fundoMunicipalService.criar(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<FundoMunicipalResponse> buscarPorId(@PathVariable UUID id) {
        try {
            FundoMunicipalResponse response = fundoMunicipalService.buscarPorId(id);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<FundoMunicipalResponse>> listarTodos() {
        List<FundoMunicipalResponse> fundos = fundoMunicipalService.listarTodos();
        return ResponseEntity.ok(fundos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(
            @PathVariable UUID id,
            @Valid @RequestBody FundoMunicipalRequest request) {
        try {
            FundoMunicipalResponse response = fundoMunicipalService.atualizar(id, request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        try {
            fundoMunicipalService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

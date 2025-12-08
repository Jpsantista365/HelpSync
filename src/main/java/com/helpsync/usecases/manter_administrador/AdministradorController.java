package com.helpsync.usecases.manter_administrador;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/administradores")
@RequiredArgsConstructor
public class AdministradorController {
    
    private final AdministradorService administradorService;
    
    @PostMapping
    public ResponseEntity<AdministradorResponse> criar(@Valid @RequestBody AdministradorRequest request) {
        try {
            AdministradorResponse response = administradorService.criar(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<AdministradorResponse> buscarPorId(@PathVariable UUID id) {
        try {
            AdministradorResponse response = administradorService.buscarPorId(id);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping
    public ResponseEntity<List<AdministradorResponse>> listarTodos() {
        try {
            List<AdministradorResponse> administradores = administradorService.listarTodos();
            return ResponseEntity.ok(administradores);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<AdministradorResponse> atualizar(
            @PathVariable UUID id,
            @Valid @RequestBody AdministradorRequest request) {
        try {
            AdministradorResponse response = administradorService.atualizar(id, request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        try {
            administradorService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/email/{email}")
    public ResponseEntity<AdministradorResponse> buscarPorEmail(@PathVariable String email) {
        try {
            AdministradorResponse response = administradorService.buscarPorEmail(email);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
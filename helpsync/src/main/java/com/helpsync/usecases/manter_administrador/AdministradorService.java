package com.helpsync.usecases.manter_administrador;

import com.helpsync.entity.Administrador;
import lombok.RequiredArgsConstructor;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder; 
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class AdministradorService {
    
    private final AdministradorRepository administradorRepository;
    private final PasswordEncoder passwordEncoder; 

    public AdministradorResponse criar(AdministradorRequest request) {
        if (administradorRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("Email já está em uso");
        }
        
        String senhaCriptografada = passwordEncoder.encode(request.senha());
        
        Administrador administrador = new Administrador(
                request.nome(),
                request.email(),
                senhaCriptografada 
        );
        
        Administrador salvo = administradorRepository.save(administrador);
        return AdministradorResponse.fromEntity(salvo);
    }
    
    @Transactional(readOnly = true)
    public AdministradorResponse buscarPorId(UUID id) {
        Administrador administrador = administradorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Administrador não encontrado com ID: " + id));
        return AdministradorResponse.fromEntity(administrador);
    }
    
    @Transactional(readOnly = true)
    public List<AdministradorResponse> listarTodos() {
        return administradorRepository.findAll()
                .stream()
                .map(AdministradorResponse::fromEntity)
                .toList();
    }
    
    public AdministradorResponse atualizar(UUID id, AdministradorRequest request) {
        Administrador administrador = administradorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Administrador não encontrado com ID: " + id));        
        if (!administrador.getEmail().equals(request.email()) && 
            administradorRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("Email já está em uso");
        }
        
        administrador.setNome(request.nome());
        administrador.setEmail(request.email());
        
        if (request.senha() != null && !request.senha().isBlank()) {
            administrador.setSenha(passwordEncoder.encode(request.senha()));
        }
        
        Administrador atualizado = administradorRepository.save(administrador);
        return AdministradorResponse.fromEntity(atualizado);
    }
    
    public void deletar(UUID id) {
        if (!administradorRepository.existsById(id)) {
            throw new RuntimeException("Administrador não encontrado com ID: " + id);
        }
        administradorRepository.deleteById(id);
    }
    
    @Transactional(readOnly = true)
    public AdministradorResponse buscarPorEmail(String email) {
        Administrador administrador = administradorRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Administrador não encontrado com email: " + email));
        return AdministradorResponse.fromEntity(administrador);
    }
    
}
package com.helpsync.usecases.autenticacao;

import com.helpsync.infra.security.TokenService;
import com.helpsync.usecases.manter_administrador.AdministradorRepository;
import com.helpsync.usecases.manter_doador.DoadorRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AdministradorRepository administradorRepository;
    private final DoadorRepository doadorRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
        
        var admin = administradorRepository.findByEmail(data.email());
        if (admin.isPresent()) {
            if (passwordEncoder.matches(data.senha(), admin.get().getSenha())) {
                String token = tokenService.gerarToken(data.email());
                return ResponseEntity.ok(new LoginResponseDTO(token));
            }
        }

        var doador = doadorRepository.findByEmail(data.email());
        if (doador.isPresent()) {
            if (passwordEncoder.matches(data.senha(), doador.get().getSenha())) {
                String token = tokenService.gerarToken(data.email());
                return ResponseEntity.ok(new LoginResponseDTO(token));
            }
        }

        return ResponseEntity.badRequest().build();
    }
}

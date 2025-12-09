package com.helpsync.infra.security;

import com.helpsync.usecases.manter_administrador.AdministradorRepository;
import com.helpsync.usecases.manter_doador.DoadorRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    TokenService tokenService;

    @Autowired
    AdministradorRepository administradorRepository;

    @Autowired
    DoadorRepository doadorRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = recuperarToken(request);

        if (token != null) {
            var login = tokenService.validarToken(token);

            if (!login.isEmpty()) {
                var admin = administradorRepository.findByEmail(login);
                
                if (admin.isPresent()) {
                    var authentication = new UsernamePasswordAuthenticationToken(admin.get(), null, Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN")));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } else {
                    var doador = doadorRepository.findByEmail(login);
                    if (doador.isPresent()) {
                         var authentication = new UsernamePasswordAuthenticationToken(doador.get(), null, Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
                         SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
            }
        }
        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
}
